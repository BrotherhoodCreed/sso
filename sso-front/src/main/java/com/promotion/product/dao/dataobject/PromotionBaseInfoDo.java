package com.promotion.product.dao.dataobject;


import java.math.BigDecimal;
import java.util.Date;


public class PromotionBaseInfoDo {
    /**
     * 主键
     */
    private Long id;
    /**
     * 促销编码
     */
    private String  activityCode;
    /**
     * 活动类型
     */
    private String  activityType;
    /**
     * '促销开始时间'
     */
    private String  salesStartTime;
    /**
     * '促销结束时间'
     */
    private Date    salesEndTime;
    /**
     * '每台限制张数'
     */
    private Integer amount;
    /**
     * '回款周期'
     */
    private Integer billCycle;
    /**
     * '活动描述'
     */
    private String  description;
    /**
     * '七字描述'
     */
    private String  introduction;
    /**
     * '团购网站'
     */
    private String  channel;
    /**
     * '团购形式'
     */
    private String  theWay;
    /**
     * '共享活动'
     */
    private String  sharedActivity;
    /**
     * '销售单价'
     */
    private BigDecimal sellingPrice;
    /**
     * '回款单价'
     */
    private BigDecimal billPrice;
    /**
     * '手续费'
     */
    private BigDecimal handlingFee;
    /**
     * '税率'
     */
    private BigDecimal taxRate;
    /**
     * '其他'
     */
    private String      other;

    /**
     * '提交状态'
     */
    private String      submit;
    /**
     * '单据类型'
     */
    private String      type;

    private Date        createdTime;
    private String      createdUser;
    private Boolean     deleted;
    private Date        updatedTime;
    private Date        updatedUser;

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

    public String getSalesStartTime() {
        return salesStartTime;
    }

    public void setSalesStartTime(String salesStartTime) {
        this.salesStartTime = salesStartTime;
    }

    public Date getSalesEndTime() {
        return salesEndTime;
    }

    public void setSalesEndTime(Date salesEndTime) {
        this.salesEndTime = salesEndTime;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getBillCycle() {
        return billCycle;
    }

    public void setBillCycle(Integer billCycle) {
        this.billCycle = billCycle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTheWay() {
        return theWay;
    }

    public void setTheWay(String theWay) {
        this.theWay = theWay;
    }

    public String getSharedActivity() {
        return sharedActivity;
    }

    public void setSharedActivity(String sharedActivity) {
        this.sharedActivity = sharedActivity;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal getBillPrice() {
        return billPrice;
    }

    public void setBillPrice(BigDecimal billPrice) {
        this.billPrice = billPrice;
    }

    public BigDecimal getHandlingFee() {
        return handlingFee;
    }

    public void setHandlingFee(BigDecimal handlingFee) {
        this.handlingFee = handlingFee;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(Date updatedUser) {
        this.updatedUser = updatedUser;
    }

    @Override
    public String toString() {
        return "PromotionBaseInfoDo{" +
                "id=" + id +
                ", activityCode='" + activityCode + '\'' +
                ", activityType='" + activityType + '\'' +
                ", salesStartTime='" + salesStartTime + '\'' +
                ", salesEndTime=" + salesEndTime +
                ", amount=" + amount +
                ", billCycle=" + billCycle +
                ", description='" + description + '\'' +
                ", introduction='" + introduction + '\'' +
                ", channel='" + channel + '\'' +
                ", theWay='" + theWay + '\'' +
                ", sharedActivity='" + sharedActivity + '\'' +
                ", sellingPrice=" + sellingPrice +
                ", billPrice=" + billPrice +
                ", handlingFee=" + handlingFee +
                ", taxRate=" + taxRate +
                ", other='" + other + '\'' +
                ", submit='" + submit + '\'' +
                ", type='" + type + '\'' +
                ", createdTime=" + createdTime +
                ", createdUser='" + createdUser + '\'' +
                ", deleted=" + deleted +
                ", updatedTime=" + updatedTime +
                ", updatedUser=" + updatedUser +
                '}';
    }
}
