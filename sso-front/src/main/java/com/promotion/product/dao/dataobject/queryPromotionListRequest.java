package com.promotion.product.dao.dataobject;

import lombok.Data;

@Data
public class queryPromotionListRequest {
    private Integer pageSize=10;
    private Integer pageIndex=1;
    private String activityCode;
}
