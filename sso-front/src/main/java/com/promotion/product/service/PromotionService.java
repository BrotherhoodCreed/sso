package com.promotion.product.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mchange.v1.lang.BooleanUtils;
import com.promotion.product.dao.dataobject.*;
import com.promotion.product.dao.mysql.PromotionBaseInfoDao;
import com.promotion.product.dao.mysql.PromotionMapperDao;
import com.promotion.product.entity.BasePageResponse;
import com.promotion.product.entity.FormTypeEnums;
import com.promotion.product.entity.SubmitEnums;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PromotionService {
    @Autowired
    private PromotionBaseInfoDao promotionBaseInfoDao;

    @Autowired
    private PromotionMapperDao promotionMapperDao;


    public PromotionBaseInfoDo queryPromotionBaseInfo(Long id) {
        return promotionBaseInfoDao.selectOneData(id);
    }


    public Boolean deletePromotionBaseById(Long id){
        return promotionBaseInfoDao.deletePromotionBaseById(id)>0;
    }

    public Boolean deletePromotionById(Long id){
        return promotionMapperDao.deletePromotionById(id)>0;
    }

    public   BasePageResponse<queryPromotionListRespone>queryPromotionList(queryPromotionListRequest request){
        BasePageResponse<queryPromotionListRespone> response=BasePageResponse.success(BasePageResponse.class);
        PageHelper.startPage(request.getPageIndex(), request.getPageSize());
        List<queryPromotionListDo> queryPromotionList=promotionBaseInfoDao.queryPromotionList(request);
        if(CollectionUtils.isEmpty(queryPromotionList)){
            return response;
        }
        PageInfo<queryPromotionListRespone> pageInfo=new PageInfo<>();
        response.setTotal(pageInfo.getTotal());
        response.setPages(pageInfo.getPageNum());
        List<queryPromotionListRespone> queryPromotionListResponeList =new ArrayList<>();
        for (queryPromotionListDo queryPromotionListDo : queryPromotionList) {
            queryPromotionListRespone queryPromotionListRespone =new queryPromotionListRespone();
            queryPromotionListRespone.setId(queryPromotionListDo.getId());
            queryPromotionListRespone.setActivityCode(queryPromotionListDo.getActivityCode());
            queryPromotionListRespone.setActivityType(queryPromotionListDo.getActivityType());
            queryPromotionListRespone.setSalesStartTime(queryPromotionListDo.getSalesStartTime());
            queryPromotionListRespone.setSalesEndTime(queryPromotionListDo.getSalesEndTime());
            queryPromotionListResponeList.add(queryPromotionListRespone);
        }
         response.setData(queryPromotionListResponeList);
       return response;
    }

    @Transactional(value = "promotionBaseInfoTransactionManager", rollbackFor = Exception.class)
    public Boolean savePromotionBaseInfo(SavePromotionBaseInfoRequery savePromotionBaseInfoRequery) {
        Boolean result = Boolean.FALSE;
        PromotionBaseInfoDo promotionBaseInfoDo = savePromotionBaseInfoRequery.getPromotionBaseInfoDo();
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
        Integer row=0;
        row=  promotionBaseInfoDao.insert(promotionBaseInfoDo);
        if (CollectionUtils.isNotEmpty(savePromotionBaseInfoRequery.getPromotionMapperDo())) {
            savePromotionBaseInfoRequery.getPromotionMapperDo().forEach(item -> {
                item.setActivityCode(promotionBaseInfoDo.getActivityCode());
                promotionMapperDao.insert(item);
            });
        }
        result =row>0;
        return result;
    }


    public Boolean updatePromotionBaseInfo(UpdatePromotionBaseInfoRequery requery) {
         Boolean result  = promotionBaseInfoDao.update(requery) > 0;
         return result;
    }
}
