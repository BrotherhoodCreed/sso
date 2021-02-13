package com.promotion.product.dao.dataobject;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PromotionBaseInfoDo {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 促销编码
     */
    private String activity;
    /**
     * 活动类型
     */
    private String activity_type;
    /**
     * '促销开始时间'
     */
    private String sales_start_time;
    /**
     * '促销结束时间'
     */
    private Date sales_end_time;
    /**
     * '每台限制张数'
     */
    private Integer amount;
    /**
     * '回款周期'
     */
    private Integer bill_cycle;
    /**
     * '活动描述'
     */
    private String description;
    /**
     * '七字描述'
     */
    private String introduction;
    /**
     * '团购网站'
     */
    private String channel;
    /**
     * '团购形式'
     */
    private String the_way;
    /**
     * '共享活动'
     */
    private String shared_activity;
    /**
     * '销售单价'
     */
    private BigDecimal selling_price;
    /**
     * '回款单价'
     */
    private BigDecimal bill_price;
    /**
     * '手续费'
     */
    private BigDecimal handling_fee;
    /**
     * '税率'
     */
    private BigDecimal tax_rate;
    /**
     * '其他'
     */
    private String other;

    /**
     * '提交状态'
     */
    private String submit;
    /**
     * '单据类型'
     */
    private String type;

    private Date created_time;
    private String created_user;
    private Boolean deleted;
    private Date updated_time;
    private Date updated_user;
}
