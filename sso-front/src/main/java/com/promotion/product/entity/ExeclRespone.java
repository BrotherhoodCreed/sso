package com.promotion.product.entity;

import com.promotion.product.common.ExcelColumn;
import lombok.Data;

@Data
public class ExeclRespone {
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
    @ExcelColumn(value ="每台限用张数/金额",col = 10)
    private String amount;
    @ExcelColumn(value ="回款周期",col = 11)
    private String billCycle;
    @ExcelColumn(value ="七字描述",col = 12)
    private String introduction;
    @ExcelColumn(value ="活动有效开始时间",col = 13)
    private String usageStartTime;
    @ExcelColumn(value ="活动有效结束时间",col = 14)
    private String usageEndTime;
    @ExcelColumn(value ="与本活动共享活动",col = 15)
    private String sharedActivity;
    @ExcelColumn(value ="销售单价",col = 16)
    private String sellingPrice;
    @ExcelColumn(value ="回款单价",col = 17)
    private String billPrice;
    @ExcelColumn(value ="手续费",col = 18)
    private String handlingFee;
    @ExcelColumn(value ="手续费税率%",col = 18)
    private String taxRate;


}
