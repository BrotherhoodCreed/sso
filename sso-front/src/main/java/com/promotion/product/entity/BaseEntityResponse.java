package com.promotion.product.entity;

import java.lang.Object;

public class BaseEntityResponse<T> extends BaseResponse {
    private static final long serialVersionUID = 1L;
    private T data;

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BaseEntityResponse() {
    }

    public BaseEntityResponse(BaseResponse response) {
        super(response);
    }

    public BaseEntityResponse(int code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    public BaseEntityResponse(int code, String message) {
            this(code, message, null);
    }

    public BaseEntityResponse(BizErrorEnum bizErrorEnum) {
        this(bizErrorEnum.getCode(), bizErrorEnum.getDesc());
    }

    public BaseEntityResponse(T data) {
        this(BizErrorEnum.SUCCESS.getCode(), BizErrorEnum.SUCCESS.getDesc(), data);
    }

    public String toString() {
        return "BaseEntityResponse(data=" + this.getData() + ")";
    }
}
