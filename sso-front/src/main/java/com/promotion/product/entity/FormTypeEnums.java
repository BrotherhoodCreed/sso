package com.promotion.product.entity;

public enum FormTypeEnums {
    TAKE_OUT("TAKE_OUT ","外卖","W"),
    EAT_IN("EAT_IN","堂食","T");
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

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4};
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }
}
