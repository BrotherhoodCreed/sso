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
                '}';
    }
}
