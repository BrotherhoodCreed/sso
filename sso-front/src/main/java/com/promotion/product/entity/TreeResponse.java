package com.promotion.product.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeResponse {
    private String title;
    private String id;
    private Integer level;
    private Boolean checked=false;
    private List<TreeResponse> children=new ArrayList<>();
}
