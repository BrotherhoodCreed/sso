package com.promotion.product.dao.dataobject;

import lombok.Data;

@Data
public class StoreAccountInfoDo {
    private String stcd;
    private String stnm;
    private Integer type;
    private String  accountnumber;
    private String uid;
    private String depositbank;
    private Boolean isdefault;

}
