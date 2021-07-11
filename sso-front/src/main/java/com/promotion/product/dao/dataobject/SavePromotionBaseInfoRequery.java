package com.promotion.product.dao.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;


import java.util.Date;
import java.util.List;

@Data
public class SavePromotionBaseInfoRequery {
//    private  PromotionBaseInfoDo promotionBaseInfoDo;
//    private List<PromotionMapperDo> promotionMapperDo;
    private  List<PromotionMapperDo> promotionMapperDo;

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
    private List<String>  sharedActivity;
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

    private Date        createdTime;
    private String      createdUser;
    private Boolean     deleted;

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

}
