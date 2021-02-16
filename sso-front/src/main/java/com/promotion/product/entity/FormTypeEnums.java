package com.promotion.product.entity;

public enum FormTypeEnums {
    TAKE_OUT("TAKE_OUT ","外卖","1"),
    EAT_IN("EAT_IN","堂食","0");
    private String code;
    private String desc;
    private String index;
    FormTypeEnums(String code, String desc, String index){
        this.code=code;
        this.desc=desc;
        this.index=index;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getIndex() {
        return index;
    }
}
