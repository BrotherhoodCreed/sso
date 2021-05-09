package com.promotion.product.entity;

import com.promotion.product.common.ExcelColumn;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExeclResponeTs extends ExeclRespone  {

    @ExcelColumn(value ="合同收售数量",col = 19)
    private Integer contractAmount;

    @ExcelColumn(value ="预付金额",col = 20)
    private BigDecimal prepaymentAmount;

    @ExcelColumn(value ="人员费用",col = 21)
    private BigDecimal wage;

    @ExcelColumn(value ="广告费用",col = 22)
    private BigDecimal advertisingFee;

    @ExcelColumn(value ="试吃费用",col = 23)
    private BigDecimal testFee;

    @ExcelColumn(value ="尊享卷费用",col = 24)
    private BigDecimal couponFee;

    @ExcelColumn(value ="尊享卷费有效期",col = 25)
    private String couponEffectiveTime;

    @ExcelColumn(value ="折扣费用",col = 26)
    private BigDecimal discountFee;

    @ExcelColumn(value ="回款人姓名",col = 27)
    private String billUserName;

    @ExcelColumn(value ="回款人账号",col = 28)
    private String billAccountNumber;


}
