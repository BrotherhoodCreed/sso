package com.promotion.product.entity;

import lombok.Data;

@Data
public class UserRoleDto {
    private Integer id;
    private String roleCode;
    private String userMobile;
    private String userName;
    private Integer isDeleted;
    private String roleDesc;
}
