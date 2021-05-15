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
    private String  receivingAccount;
    private String  receivingNumber;
    private Boolean deleted;
    private String  billUserName;
    private String  billAccountNumber;
    private String  billDepositBank;


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

    public String getReceivingAccount() {
        return receivingAccount;
    }

    public void setReceivingAccount(String receivingAccount) {
        this.receivingAccount = receivingAccount;
    }

    public String getReceivingNumber() {
        return receivingNumber;
    }

    public void setReceivingNumber(String receivingNumber) {
        this.receivingNumber = receivingNumber;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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

    public String getBillDepositBank() {
        return billDepositBank;
    }

    public void setBillDepositBank(String billDepositBank) {
        this.billDepositBank = billDepositBank;
    }

    @Override
    public String toString() {
        return "PromotionMapperDo{" +
                "id=" + id +
                ", activityCode='" + activityCode + '\'' +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", restaurantCode='" + restaurantCode + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", receivingAccount='" + receivingAccount + '\'' +
                ", receivingNumber='" + receivingNumber + '\'' +
                ", deleted=" + deleted +
                ", billUserName='" + billUserName + '\'' +
                ", billAccountNumber='" + billAccountNumber + '\'' +
                ", billDepositBank='" + billDepositBank + '\'' +
                '}';
    }
}
