package com.promotion.product.dao.dataobject;

import lombok.Data;

@Data
public class UserRoleDo {
    private Integer id;
    private String roleCode;
    private String roleDesc;
    private String userMobile;
    private String userName;
    private Integer isDeleted;

}
