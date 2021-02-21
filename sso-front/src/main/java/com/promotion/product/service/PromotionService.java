package com.promotion.product.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Joiner;
import com.promotion.product.common.ModelCopier;
import com.promotion.product.dao.dataobject.*;
import com.promotion.product.dao.mysql.PromotionBaseInfoDao;
import com.promotion.product.dao.mysql.PromotionMapperDao;
import com.promotion.product.entity.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PromotionService {
    @Autowired
    private PromotionBaseInfoDao promotionBaseInfoDao;

    @Autowired
    private PromotionMapperDao promotionMapperDao;


    public PromotionBaseInfoRespone queryPromotionBaseInfo(String activityCode) {
        PromotionBaseInfoDo promotionBaseInfoDo =promotionBaseInfoDao.selectOneData(activityCode);
        PromotionBaseInfoRespone promotionBaseInfoRespone=ModelCopier.copy(promotionBaseInfoDo,PromotionBaseInfoRespone.class);
        if(StringUtils.isNotEmpty(promotionBaseInfoDo.getSharedActivity())){
            promotionBaseInfoRespone.setSharedActivity(Arrays.asList(promotionBaseInfoDo.getSharedActivity().split(",")));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            if(promotionBaseInfoDo.getSalesStartTime()!=null){
                String salesStartTime = formatter.format(promotionBaseInfoDo.getSalesStartTime());
                promotionBaseInfoRespone.setSalesStartTime(salesStartTime);
            }
            if(promotionBaseInfoDo.getSalesEndTime()!=null){
                String salesEndTime = formatter.format(promotionBaseInfoDo.getSalesEndTime());
                promotionBaseInfoRespone.setSalesEndTime(salesEndTime);
            }
            if(promotionBaseInfoDo.getUsageStartTime()!=null){
                String usageStartTime = formatter.format(promotionBaseInfoDo.getUsageStartTime());
                promotionBaseInfoRespone.setUsageStartTime(usageStartTime);

            }
            if(promotionBaseInfoDo.getUsageEndTime()!=null){
                String usageEndTime = formatter.format(promotionBaseInfoDo.getUsageEndTime());
                promotionBaseInfoRespone.setUsageEndTime(usageEndTime);
            }
        }
        return promotionBaseInfoRespone;
    }

    @Transactional
    public Boolean deletePromotionBaseById(List<Long> id){
        Integer row=0;
        for (Long aLong : id) {
            promotionBaseInfoDao.deletePromotionBaseById(aLong);
        }
        return row>0;
    }

    public Boolean deletePromotionById(Long id){
        return promotionMapperDao.deletePromotionById(id)>0;
    }

    public   BasePageResponse<QueryPromotionListRespone>queryPromotionList(QueryPromotionListRequest request){
        BasePageResponse<QueryPromotionListRespone> response=BasePageResponse.success(BasePageResponse.class);
        PageHelper.startPage(request.getPageIndex(), request.getPageSize());
        List<QueryPromotionListDo> queryPromotionList=promotionBaseInfoDao.queryPromotionList(request);
        if(CollectionUtils.isEmpty(queryPromotionList)){
            return response;
        }
        PageInfo<QueryPromotionListRespone> pageInfo=new PageInfo<>();
        response.setTotal(pageInfo.getTotal());
        response.setPages(pageInfo.getPageNum());
        List<QueryPromotionListRespone> queryPromotionListResponeList =new ArrayList<>();
        for (QueryPromotionListDo QueryPromotionListDo : queryPromotionList) {
            QueryPromotionListRespone QueryPromotionListRespone =new QueryPromotionListRespone();
            QueryPromotionListRespone.setId(QueryPromotionListDo.getId());
            QueryPromotionListRespone.setActivityCode(QueryPromotionListDo.getActivityCode());
            QueryPromotionListRespone.setActivityType(QueryPromotionListDo.getActivityType());
            QueryPromotionListRespone.setSalesStartTime(QueryPromotionListDo.getSalesStartTime());
            QueryPromotionListRespone.setSalesEndTime(QueryPromotionListDo.getSalesEndTime());
            queryPromotionListResponeList.add(QueryPromotionListRespone);
        }
         response.setData(queryPromotionListResponeList);
       return response;
    }

    public SavePromotionBaseInfoRespone savePromotionBaseInfo(SavePromotionBaseInfoRequery savePromotionBaseInfoRequery)  throws Exception{
        SavePromotionBaseInfoRespone savePromotionBaseInfoRespone =new SavePromotionBaseInfoRespone();
        PromotionBaseInfoDo promotionBaseInfoDo =ModelCopier.copy(savePromotionBaseInfoRequery,PromotionBaseInfoDo.class);

        Calendar calendar = Calendar.getInstance();
        Date date=calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("YYYYMM");
        Integer index= promotionBaseInfoDao.querySerialNumber();
        //01 区域号
        String code ="01"+ FormTypeEnums.TAKE_OUT.getIndex()+ format.format(date)+String.format("%03d",index);
        promotionBaseInfoDo.setActivityCode(code);
        promotionBaseInfoDo.setCreatedTime(new Date());
        promotionBaseInfoDo.setUpdatedTime(new Date());
        promotionBaseInfoDo.setSubmit(SubmitEnums.SAVE.getCode());
        promotionBaseInfoDo.setType(FormTypeEnums.TAKE_OUT.getIndex());
        if(CollectionUtils.isNotEmpty(savePromotionBaseInfoRequery.getSharedActivity())){
            String string=Joiner.on(",").join(savePromotionBaseInfoRequery.getSharedActivity());
            promotionBaseInfoDo.setSharedActivity(string);
        }
        Boolean result=  promotionBaseInfoDao.insert(promotionBaseInfoDo)>0;
        if(result){
            savePromotionBaseInfoRespone.setActivityCode(code);
        }
        return savePromotionBaseInfoRespone;
    }

    public Boolean savePromotionMapperInfo(savePromotionMapperInfoRequest req){

        Integer row=0;
        for (PromotionMapperDo promotionMapperDo : req.getPromotionMapperDos()) {
            row+= promotionMapperDao.insert(promotionMapperDo);
        }
        return  row>0;

    }


    public Boolean updatePromotionBaseInfo(UpdatePromotionBaseInfoRequery requery) {
         Boolean result  = promotionBaseInfoDao.update(requery) > 0;
         return result;
    }
}
