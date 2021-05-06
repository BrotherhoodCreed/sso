package com.promotion.product.entity;

import org.apache.commons.lang3.StringUtils;

public enum PromotionTypeEnum {
    wm("1","detail"),
    ts("0","detailTs");
    private String code;
    private String desc;
    PromotionTypeEnum(String code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public static String getDescByCode(String code){
        if(StringUtils.isNotEmpty(code)){
            for (PromotionTypeEnum promotionTypeEnum :PromotionTypeEnum.values()){
                if(StringUtils.equals(promotionTypeEnum.code,code)){
                    return promotionTypeEnum.getDesc();
                }
            }
        }
        //code为空默认外卖
        return  PromotionTypeEnum.wm.getDesc();
    }
}
