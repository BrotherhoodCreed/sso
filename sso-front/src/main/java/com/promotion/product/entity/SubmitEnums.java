package com.promotion.product.entity;

public enum SubmitEnums {
    SAVE("SAVE","保存"),
    SUBMIT("SUBMIT","提交");
    private String code;
    private String desc;
    SubmitEnums(String code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}
