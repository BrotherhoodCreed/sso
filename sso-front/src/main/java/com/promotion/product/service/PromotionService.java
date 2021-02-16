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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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


    public   BasePageResponse<queryPromotionListRespone>queryPromotionList(queryPromotionListRequest request){
        BasePageResponse<queryPromotionListRespone> response=BasePageResponse.success(BasePageResponse.class);
        PageHelper.startPage(request.getPageIndex(), request.getPageSize());
        List<queryPromotionListDo> queryPromotionList=promotionBaseInfoDao.queryPromotionList(request);
        if(queryPromotionList==null ||queryPromotionList.size()==0){
            return response;
        }
        Map<String,List<queryPromotionListDo>> map=queryPromotionList.stream().collect(Collectors.groupingBy(item->item.getActivityCode(),Collectors.toList()));
        PageInfo<queryPromotionListRespone> pageInfo=new PageInfo<>();
        response.setTotal(pageInfo.getTotal());
        response.setPages(pageInfo.getPageNum());
        Iterator<Map.Entry<String,List<queryPromotionListDo>>> iterator=map.entrySet().iterator();
        List<queryPromotionListRespone> queryPromotionListResponeList =new ArrayList<>();
        while (iterator.hasNext()){
            queryPromotionListRespone queryPromotionListRespone =new queryPromotionListRespone();
            Map.Entry<String,List<queryPromotionListDo>> entry= iterator.next();
            List<queryPromotionListDo> queryPromotionListDoList=entry.getValue();
            queryPromotionListDo queryPromotionListDo =queryPromotionListDoList.get(0);
            queryPromotionListRespone.setActivityCode(queryPromotionListDo.getActivityCode());
            queryPromotionListRespone.setActivityType(queryPromotionListDo.getActivityType());
            queryPromotionListRespone.setSalesStartTime(queryPromotionListDo.getSalesStartTime());
            queryPromotionListRespone.setSalesEndTime(queryPromotionListDo.getSalesEndTime());
            for (queryPromotionListDo queryPromotionListDo1 : queryPromotionListDoList) {
                queryPromotionListRespone.PromotionMapper promotionMapper=new queryPromotionListRespone.PromotionMapper();
                promotionMapper.setRestaurantName(queryPromotionListDo1.getRestaurantName());
                promotionMapper.setRestaurantCode(queryPromotionListDo1.getRestaurantCode());
                promotionMapper.setArea(queryPromotionListDo1.getArea());
                promotionMapper.setCity(queryPromotionListDo1.getCity());
                queryPromotionListRespone.getPromotionMappers().add(promotionMapper);
            }
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
        if (!CollectionUtils.isEmpty(savePromotionBaseInfoRequery.getPromotionMapperDo())) {
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
