package com.promotion.product.dao.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;


public class QueryPromotionListDo {
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "queryPromotionListDo{" +
                "id=" + id +
                ", activityCode='" + activityCode + '\'' +
                ", activityType='" + activityType + '\'' +
                ", salesStartTime=" + salesStartTime +
                ", salesEndTime=" + salesEndTime +
                '}';
    }
}
