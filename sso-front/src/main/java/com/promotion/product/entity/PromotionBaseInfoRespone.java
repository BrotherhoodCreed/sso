package com.promotion.product.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class PromotionBaseInfoRespone {
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

    private String salesStartTime;
    /**
     * '促销结束时间'
     */
    private String salesEndTime;
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
    private List<String> sharedActivity;
    private String usageStartTime;
    private String usageEndTime;
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
    private String        createdTime;
    private String      createdUser;
    private Boolean     deleted;
    private String        updatedTime;
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

}
