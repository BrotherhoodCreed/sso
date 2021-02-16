package com.promotion.product.dao.dataobject;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class PromotionMapperDo {
    private Long id;
    private String activityCode;
    private String  area;
    private String  city;
    private String  restaurantCode;
    private String  restaurantName;
    private Boolean deleted;

}
