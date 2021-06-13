package com.promotion.product.dao.dataobject;

import lombok.Data;

@Data
public class UserRoleDo {
    private Integer id;
    private Integer roleCode;
    private String userMobile;
    private String userName;
    private Integer isDeleted;

}
