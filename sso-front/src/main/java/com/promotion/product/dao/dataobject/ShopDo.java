package com.promotion.product.dao.dataobject;

import lombok.Data;

@Data
public class ShopDo {

    /**
     * '门店编码'
     */
     private String    stcd;
    /**
     * '门店名称'
     */
    private String       stnm ;
    /**
     *   门店别名
     */
    private String asnm;// '',
    /**
     *   '助记码/缩写'
     */
    private String mnmnccd ;
    /**
     *   '门店类型'
     */
    private String sttpcd;
    /**
     *   '启用状态'
     */
     private Integer       sts;

    /**
     *   '品牌编码'
     */
    private   String   brndcd;

    /**
     * '商圈编码'
     */
    private  String       zncd;
    /**
     * '所属大区'
     */
    private String       am;

    /**
     *  '所属督导'
     */
    private String       su;
    /**
     *  '餐位数'
     */
    private Integer        seatnum;
    /**
     *  '餐台数'
     */
    private Integer         tablesnum;
    /**
     *  '营业面积'
     */
    private Float          oprtar;
    /**
     *  '门店经理'
     */
    private String           stmgr;
    /**
     *  '营业时间起'
     */
    private String       bistd;
    /**
     *  '营业时间止'
     */
    private String       biend;
    /**
     *  '所在省份'
     */
    private String       province;
    /**
     *  '所属城市'
     */
    private String       city;
    /**
     *  '所属区县'
     */
    private String        dstrct;
    /**
     *  '门店地址'
     */
    private String        adrs;
    /**
     *  标识POS厂商 1、辰森
     */
    private String        def5;
    /**
     *  '开店日期'
     */
    private String         sysinitdate;
    /**
     * '是否可比'
     */
    private String       iscomparable;
}
