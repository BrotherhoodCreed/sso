package com.promotion.product.entity;

import com.promotion.product.dao.dataobject.PromotionMapperDo;
import lombok.Data;

import java.util.List;

@Data
public class SavePromotionMapperInfoRequest {
    private List<PromotionMapperDo> promotionMapperDos;
}
