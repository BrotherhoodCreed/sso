package com.promotion.product.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;


public class BaseResponse implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(BaseResponse.class);
    private static final long serialVersionUID = -2970539265124203353L;
    private boolean success;
    private int code;
    private String message;

    public BaseResponse() {
    }

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResponse(BaseResponse response) {
        this.code = response.getCode();
        this.message = response.getMessage();
    }

    public static <E extends BaseResponse> E buildBaseResp(boolean success, int code, String message, Class<E> cls) {
        BaseResponse baseResponse = null;

        try {
            baseResponse = (BaseResponse)cls.newInstance();
        } catch (InstantiationException var6) {
            logger.warn("");
        } catch (IllegalAccessException var7) {
            logger.warn("");
        }

        baseResponse.setSuccess(success);
        baseResponse.setCode(code);
        baseResponse.setMessage(message);
        return (E) baseResponse;
    }

    public static <E extends BaseResponse> E success(Class<E> cls) {
        return buildBaseResp(true, BizErrorEnum.SUCCESS.getCode(), BizErrorEnum.SUCCESS.getDesc(), cls);
    }

    public static <E extends BaseResponse> E failure(Class<E> cls) {
        return buildBaseResp(false, BizErrorEnum.FAILURE.getCode(), BizErrorEnum.FAILURE.getDesc(), cls);
    }

    public static <E extends BaseResponse> E failure(String message, Class<E> cls) {
        return buildBaseResp(false, BizErrorEnum.FAILURE.getCode(), message, cls);
    }

    public static <E extends BaseResponse> E failure(BizErrorEnum errorEnum, String message, Class<E> cls) {
        return buildBaseResp(false, errorEnum.getCode(), message, cls);
    }

    public static BaseResponse failure(String message) {
        return new BaseResponse(BizErrorEnum.FAILURE.getCode(), message);
    }

    public static BaseResponse failure(BizErrorEnum errorEnum, String message) {
        return new BaseResponse(errorEnum.getCode(), message);
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }


}

