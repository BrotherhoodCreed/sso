package com.promotion.product.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeResponse {
    private String title;
    private String id;
    private String am;
    private String city;
    private Integer level;
    private String amcd;
    private String accountnumber;
    private String uid;
    private String depositbank;
    private Boolean checked=false;
    private Boolean spread=false;
    private List<TreeResponse> children=new ArrayList<>();
}
