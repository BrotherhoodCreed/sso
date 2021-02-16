package com.promotion.product.entity;

import com.github.pagehelper.Page;

import java.util.List;

public class BasePageResponse<T> extends BaseResponse {
    private static final long serialVersionUID = 1L;
    private List<T> data;
    private Long total;
    private Integer pages;

    public BasePageResponse() {
    }

    public BasePageResponse(List<T> data) {
        this.data = data;
        this.total = ((Page)data).getTotal();
        this.pages = ((Page)data).getPages();
    }

    public BasePageResponse(List<T> data, Long total, Integer pages) {
        this.data = data;
        this.total = total;
        this.pages = pages;
    }

    public BasePageResponse(List<T> data, boolean isPageHelper) {
        if (!isPageHelper) {
            this.data = data;
        }

    }

    public BasePageResponse(int code, String message) {
        super(code, message);
    }

    public BasePageResponse(BizErrorEnum bizErrorEnum) {
        this(bizErrorEnum.getCode(), bizErrorEnum.getDesc());
    }

    public void setData(final List<T> data) {
        this.data = data;
    }

    public void setTotal(final Long total) {
        this.total = total;
    }

    public void setPages(final Integer pages) {
        this.pages = pages;
    }

    public List<T> getData() {
        return this.data;
    }

    public Long getTotal() {
        return this.total;
    }

    public Integer getPages() {
        return this.pages;
    }

    public String toString() {
        return "BasePageResponse(data=" + this.getData() + ", total=" + this.getTotal() + ", pages=" + this.getPages() + ")";
    }
}
