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


    private String amcd;

    private String accountnumber;

    private String uid;

    private String depositbank;

    public String getStcd() {
        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    public String getStnm() {
        return stnm;
    }

    public void setStnm(String stnm) {
        this.stnm = stnm;
    }

    public String getAsnm() {
        return asnm;
    }

    public void setAsnm(String asnm) {
        this.asnm = asnm;
    }

    public String getMnmnccd() {
        return mnmnccd;
    }

    public void setMnmnccd(String mnmnccd) {
        this.mnmnccd = mnmnccd;
    }

    public String getSttpcd() {
        return sttpcd;
    }

    public void setSttpcd(String sttpcd) {
        this.sttpcd = sttpcd;
    }

    public Integer getSts() {
        return sts;
    }

    public void setSts(Integer sts) {
        this.sts = sts;
    }

    public String getBrndcd() {
        return brndcd;
    }

    public void setBrndcd(String brndcd) {
        this.brndcd = brndcd;
    }

    public String getZncd() {
        return zncd;
    }

    public void setZncd(String zncd) {
        this.zncd = zncd;
    }

    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am;
    }

    public String getSu() {
        return su;
    }

    public void setSu(String su) {
        this.su = su;
    }

    public Integer getSeatnum() {
        return seatnum;
    }

    public void setSeatnum(Integer seatnum) {
        this.seatnum = seatnum;
    }

    public Integer getTablesnum() {
        return tablesnum;
    }

    public void setTablesnum(Integer tablesnum) {
        this.tablesnum = tablesnum;
    }

    public Float getOprtar() {
        return oprtar;
    }

    public void setOprtar(Float oprtar) {
        this.oprtar = oprtar;
    }

    public String getStmgr() {
        return stmgr;
    }

    public void setStmgr(String stmgr) {
        this.stmgr = stmgr;
    }

    public String getBistd() {
        return bistd;
    }

    public void setBistd(String bistd) {
        this.bistd = bistd;
    }

    public String getBiend() {
        return biend;
    }

    public void setBiend(String biend) {
        this.biend = biend;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDstrct() {
        return dstrct;
    }

    public void setDstrct(String dstrct) {
        this.dstrct = dstrct;
    }

    public String getAdrs() {
        return adrs;
    }

    public void setAdrs(String adrs) {
        this.adrs = adrs;
    }

    public String getDef5() {
        return def5;
    }

    public void setDef5(String def5) {
        this.def5 = def5;
    }

    public String getSysinitdate() {
        return sysinitdate;
    }

    public void setSysinitdate(String sysinitdate) {
        this.sysinitdate = sysinitdate;
    }

    public String getIscomparable() {
        return iscomparable;
    }

    public void setIscomparable(String iscomparable) {
        this.iscomparable = iscomparable;
    }

    public String getAmcd() {
        return amcd;
    }

    public void setAmcd(String amcd) {
        this.amcd = amcd;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDepositbank() {
        return depositbank;
    }

    public void setDepositbank(String depositbank) {
        this.depositbank = depositbank;
    }

    @Override
    public String toString() {
        return "ShopDo{" +
                "stcd='" + stcd + '\'' +
                ", stnm='" + stnm + '\'' +
                ", asnm='" + asnm + '\'' +
                ", mnmnccd='" + mnmnccd + '\'' +
                ", sttpcd='" + sttpcd + '\'' +
                ", sts=" + sts +
                ", brndcd='" + brndcd + '\'' +
                ", zncd='" + zncd + '\'' +
                ", am='" + am + '\'' +
                ", su='" + su + '\'' +
                ", seatnum=" + seatnum +
                ", tablesnum=" + tablesnum +
                ", oprtar=" + oprtar +
                ", stmgr='" + stmgr + '\'' +
                ", bistd='" + bistd + '\'' +
                ", biend='" + biend + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", dstrct='" + dstrct + '\'' +
                ", adrs='" + adrs + '\'' +
                ", def5='" + def5 + '\'' +
                ", sysinitdate='" + sysinitdate + '\'' +
                ", iscomparable='" + iscomparable + '\'' +
                ", amcd='" + amcd + '\'' +
                ", accountnumber='" + accountnumber + '\'' +
                ", uid='" + uid + '\'' +
                ", depositbank='" + depositbank + '\'' +
                '}';
    }
}
