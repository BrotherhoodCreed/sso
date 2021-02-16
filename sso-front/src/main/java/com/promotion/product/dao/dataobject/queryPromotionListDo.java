package com.promotion.product.dao.dataobject;

import lombok.Data;

import java.util.Date;
import java.util.List;

public class queryPromotionListDo {
    private Long id;
    /**
     *区域
     */
    private String area;
    /**
     *城市
     */
    private String city;
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

    private String restaurantName;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRestaurantCode() {
        return restaurantCode;
    }

    public void setRestaurantCode(String restaurantCode) {
        this.restaurantCode = restaurantCode;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Date getSalesStartTime() {
        return salesStartTime;
    }

    public void setSalesStartTime(Date salesStartTime) {
        this.salesStartTime = salesStartTime;
    }

    public Date getSalesEndTime() {
        return salesEndTime;
    }

    public void setSalesEndTime(Date salesEndTime) {
        this.salesEndTime = salesEndTime;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "queryPromotionListDo{" +
                "id=" + id +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", restaurantCode='" + restaurantCode + '\'' +
                ", activityCode='" + activityCode + '\'' +
                ", activityType='" + activityType + '\'' +
                ", salesStartTime=" + salesStartTime +
                ", salesEndTime=" + salesEndTime +
                ", restaurantName='" + restaurantName + '\'' +
                '}';
    }
}
