package com.promotion.product.entity;

import lombok.Data;

@Data
public class UserRoleDto {
    private Integer id;
    private Integer roleCode;
    private String userMobile;
    private String userName;
    private Integer isDeleted;
}
