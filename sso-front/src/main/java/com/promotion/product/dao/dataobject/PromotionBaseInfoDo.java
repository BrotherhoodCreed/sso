package com.promotion.product.dao.dataobject;


import com.fasterxml.jackson.annotation.JsonFormat;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date salesStartTime;
    /**
     * '促销结束时间'
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date salesEndTime;
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
    private String sharedActivity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date usageStartTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date usageEndTime;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date        createdTime;
    private String      createdUser;
    private Boolean     deleted;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date        updatedTime;
    private String        updatedUser;
    /**
     * '合同收售数量'
     */
    private Integer contractAmount;
    /**
     * '预付金额'
     */
    private BigDecimal prepaymentAmount;
    /**
     * '人员费用'
     */
    private BigDecimal wage;
    /**
     * '广告费用'
     */
    private BigDecimal advertisingFee;
    /**
     * '试吃费用'
     */
    private BigDecimal testFee;
    /**
     * '尊享卷费用'
     */
    private BigDecimal couponFee;
    /**
     * '尊享卷费有效期'
     */
    private String couponEffectiveTime;
    /**
     * '折扣费用'
     */
    private BigDecimal discountFee;

    /**
     * '回款人姓名'
     */
    private String billUserName;
    /**
     * '回款人账号'
     */
    private String billAccountNumber;


    /**
     * 是否存在回款行号  0是 1否
     */
    private Integer isAnyBillAccount;
    /**
     *套餐原价
     */
    private BigDecimal packageOriginalPrice;
    /**
     * 套餐费率
     */
    private BigDecimal packageDiscountRate;



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

    public Date getUsageStartTime() {
        return usageStartTime;
    }

    public void setUsageStartTime(Date usageStartTime) {
        this.usageStartTime = usageStartTime;
    }

    public Date getUsageEndTime() {
        return usageEndTime;
    }

    public void setUsageEndTime(Date usageEndTime) {
        this.usageEndTime = usageEndTime;
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

    public String getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    public Integer getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(Integer contractAmount) {
        this.contractAmount = contractAmount;
    }

    public BigDecimal getPrepaymentAmount() {
        return prepaymentAmount;
    }

    public void setPrepaymentAmount(BigDecimal prepaymentAmount) {
        this.prepaymentAmount = prepaymentAmount;
    }

    public BigDecimal getWage() {
        return wage;
    }

    public void setWage(BigDecimal wage) {
        this.wage = wage;
    }

    public BigDecimal getAdvertisingFee() {
        return advertisingFee;
    }

    public void setAdvertisingFee(BigDecimal advertisingFee) {
        this.advertisingFee = advertisingFee;
    }

    public BigDecimal getTestFee() {
        return testFee;
    }

    public void setTestFee(BigDecimal testFee) {
        this.testFee = testFee;
    }

    public BigDecimal getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(BigDecimal couponFee) {
        this.couponFee = couponFee;
    }

    public String getCouponEffectiveTime() {
        return couponEffectiveTime;
    }

    public void setCouponEffectiveTime(String couponEffectiveTime) {
        this.couponEffectiveTime = couponEffectiveTime;
    }

    public BigDecimal getDiscountFee() {
        return discountFee;
    }

    public void setDiscountFee(BigDecimal discountFee) {
        this.discountFee = discountFee;
    }

    public String getBillUserName() {
        return billUserName;
    }

    public void setBillUserName(String billUserName) {
        this.billUserName = billUserName;
    }

    public String getBillAccountNumber() {
        return billAccountNumber;
    }

    public void setBillAccountNumber(String billAccountNumber) {
        this.billAccountNumber = billAccountNumber;
    }

    public Integer getIsAnyBillAccount() {
        return isAnyBillAccount;
    }

    public void setIsAnyBillAccount(Integer isAnyBillAccount) {
        this.isAnyBillAccount = isAnyBillAccount;
    }

    public BigDecimal getPackageOriginalPrice() {
        return packageOriginalPrice;
    }

    public void setPackageOriginalPrice(BigDecimal packageOriginalPrice) {
        this.packageOriginalPrice = packageOriginalPrice;
    }

    public BigDecimal getPackageDiscountRate() {
        return packageDiscountRate;
    }

    public void setPackageDiscountRate(BigDecimal packageDiscountRate) {
        this.packageDiscountRate = packageDiscountRate;
    }

    @Override
    public String toString() {
        return "PromotionBaseInfoDo{" +
                "id=" + id +
                ", activityCode='" + activityCode + '\'' +
                ", activityType='" + activityType + '\'' +
                ", salesStartTime=" + salesStartTime +
                ", salesEndTime=" + salesEndTime +
                ", amount=" + amount +
                ", billCycle=" + billCycle +
                ", description='" + description + '\'' +
                ", introduction='" + introduction + '\'' +
                ", channel='" + channel + '\'' +
                ", theWay='" + theWay + '\'' +
                ", sharedActivity='" + sharedActivity + '\'' +
                ", usageStartTime=" + usageStartTime +
                ", usageEndTime=" + usageEndTime +
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
                ", updatedUser='" + updatedUser + '\'' +
                ", contractAmount=" + contractAmount +
                ", prepaymentAmount=" + prepaymentAmount +
                ", wage=" + wage +
                ", advertisingFee=" + advertisingFee +
                ", testFee=" + testFee +
                ", couponFee=" + couponFee +
                ", couponEffectiveTime='" + couponEffectiveTime + '\'' +
                ", discountFee=" + discountFee +
                ", billUserName='" + billUserName + '\'' +
                ", billAccountNumber='" + billAccountNumber + '\'' +
                ", isAnyBillAccount=" + isAnyBillAccount +
                ", packageOriginalPrice=" + packageOriginalPrice +
                ", packageDiscountRate=" + packageDiscountRate +
                '}';
    }
}
