package com.promotion.product.service;

import com.promotion.product.dao.dataobject.PromotionMapperDo;
import com.promotion.product.dao.mysql.PromotionMapperDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionMapperSeriver {
    @Autowired
    private PromotionMapperDao promotionMapperDao;

    public List<PromotionMapperDo> queryByPromotionBaseInfoId(Long promotionBaseInfoId){
         return promotionMapperDao.selectByPromotionBaseInfoId(promotionBaseInfoId);
    }
}
