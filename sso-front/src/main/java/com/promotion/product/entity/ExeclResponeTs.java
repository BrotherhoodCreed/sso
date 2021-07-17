package com.promotion.product.entity;

import com.promotion.product.common.ExcelColumn;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExeclResponeTs {
    @ExcelColumn(value ="区域",col = 1)
    private String area;
    @ExcelColumn(value ="城市",col = 2)
    private String city;
    @ExcelColumn(value ="餐厅编码",col = 3)
    private String restaurantCode;
    @ExcelColumn(value ="餐厅名",col = 4)
    private String restaurantName;
    @ExcelColumn(value ="促销编号",col = 5)
    private String activityCode;
    @ExcelColumn(value ="活动类型",col = 6)
    private String activityType;
    @ExcelColumn(value ="开始时间",col = 7)
    private String salesStartTime;
    @ExcelColumn(value ="结束时间",col = 8)
    private String salesEndTime;
    @ExcelColumn(value ="活动描述",col = 9)
    private String description;
    @ExcelColumn(value ="每台限用张数/金额",col = 10,type=1)
    private String amount;
    @ExcelColumn(value ="回款周期",col = 11)
    private String billCycle;
    @ExcelColumn(value ="键位名称",col = 12)
    private String introduction;
    @ExcelColumn(value ="活动有效开始时间",col = 13)
    private String usageStartTime;
    @ExcelColumn(value ="活动有效结束时间",col = 14)
    private String usageEndTime;
    @ExcelColumn(value ="与本活动共享活动",col = 15)
    private String sharedActivity;
    @ExcelColumn(value ="销售单价",col = 16,type=2)
    private String sellingPrice;
    @ExcelColumn(value ="回款单价",col = 17,type=2)
    private String billPrice;
    @ExcelColumn(value ="手续费",col = 18,type=2)
    private String handlingFee;
    @ExcelColumn(value ="手续费税率%",col = 18,type = 3)
    private String taxRate;

    @ExcelColumn(value ="合同收售数量",col = 19,type=1)
    private Integer contractAmount;

//    @ExcelColumn(value ="预付金额",col = 20)
//    private BigDecimal prepaymentAmount;
//
//    @ExcelColumn(value ="人员费用",col = 21)
//    private BigDecimal wage;
//
//    @ExcelColumn(value ="广告费用",col = 22)
//    private BigDecimal advertisingFee;
//
//    @ExcelColumn(value ="试吃费用",col = 23)
//    private BigDecimal testFee;
//
//    @ExcelColumn(value ="尊享卷费用",col = 24)
//    private BigDecimal couponFee;
//
//    @ExcelColumn(value ="尊享卷费有效期",col = 25)
//    private String couponEffectiveTime;
//
//    @ExcelColumn(value ="折扣费用",col = 26)
//    private BigDecimal discountFee;

    @ExcelColumn(value ="回款人姓名",col = 20)
    private String billUserName;

    @ExcelColumn(value ="回款人账号",col = 21)
    private String billAccountNumber;

    @ExcelColumn(value ="是否回款账号",col = 21)
    private String isAnyBillAccount;

    /**
     * 套餐原价
     */
    @ExcelColumn(value ="套餐原价",col = 22)
    private BigDecimal packageOriginalPrice;

    /**
     * 套餐折扣
     */
    @ExcelColumn(value ="套餐折扣",col = 23,type = 3)
    private String packageDiscountRate;


}
