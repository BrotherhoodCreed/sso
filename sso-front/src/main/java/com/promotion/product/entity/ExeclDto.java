package com.promotion.product.entity;



import java.math.BigDecimal;
import java.util.Date;

public class ExeclDto {
    private String area;
    private String city;
    private String restaurantCode;
    private String restaurantName;
    private String activityCode;
    private String activityType;
    private Date salesStartTime;
    private Date salesEndTime;
    private String description;
    private Integer amount;
    private String billCycle;
    private String introduction;
    private Date usageStartTime;
    private Date usageEndTime;
    private String sharedActivity;
    private BigDecimal sellingPrice;
    private BigDecimal billPrice;
    private BigDecimal handlingFee;
    private BigDecimal  taxRate;
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
     * 是否存在回款账号 0 是 1 否
     */
    private Boolean isAnyBillAccount;

    /**
     * 套餐原价
     */
    private BigDecimal packageOriginalPrice;

    /**
     * 套餐折扣价
     */
    private BigDecimal packageDiscountRate;

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

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getBillCycle() {
        return billCycle;
    }

    public void setBillCycle(String billCycle) {
        this.billCycle = billCycle;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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

    @Override
    public String toString() {
        return "ExeclDto{" +
                "area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", restaurantCode='" + restaurantCode + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", activityCode='" + activityCode + '\'' +
                ", activityType='" + activityType + '\'' +
                ", salesStartTime=" + salesStartTime +
                ", salesEndTime=" + salesEndTime +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", billCycle='" + billCycle + '\'' +
                ", introduction='" + introduction + '\'' +
                ", usageStartTime=" + usageStartTime +
                ", usageEndTime=" + usageEndTime +
                ", sharedActivity='" + sharedActivity + '\'' +
                ", sellingPrice=" + sellingPrice +
                ", billPrice=" + billPrice +
                ", handlingFee=" + handlingFee +
                ", taxRate=" + taxRate +
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
                '}';
    }
}
