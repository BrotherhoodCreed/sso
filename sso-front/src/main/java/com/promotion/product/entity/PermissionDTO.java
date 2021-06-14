package com.promotion.product.entity;

import lombok.Data;

import java.util.List;

@Data
public class PermissionDTO {
    private List<String> roleCodes;
    //权限描述 用于列表页展示 多个权限用逗号隔开拼接成字符串
    private String roledesc;
    private String name;
    private String mobile;
    private String id;
}
