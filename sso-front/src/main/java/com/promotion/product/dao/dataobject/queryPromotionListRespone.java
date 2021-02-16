package com.promotion.product.dao.dataobject;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class queryPromotionListRespone {

    /**
     *餐厅编码
     */
    private String restaurantCode;
    /**
     *促销编码
     */
    private String activityCode;
    /**
     *活动类型
     */
    private String activityType;
    /**
     *开始时间
     */
    private Date salesStartTime;
    /**
     *结束时间
     */
    private Date salesEndTime;
    /**
     *对位栏
     */
    private List<PromotionMapper> promotionMappers =new ArrayList<>();


    @Data
    public static class  PromotionMapper{
        /**
         *区域
         */
        private String area;
        /**
         *城市
         */
        private String city;
        private String restaurantName;
        private String restaurantCode;
    }



}
