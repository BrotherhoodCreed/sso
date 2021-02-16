package com.promotion.product.dao.dataobject;

import lombok.Data;

import java.util.List;

@Data
public class SavePromotionBaseInfoRequery {
    private  PromotionBaseInfoDo promotionBaseInfoDo;
    private List<PromotionMapperDo> promotionMapperDo;
}
