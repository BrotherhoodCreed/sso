package com.promotion.product.entity;

import lombok.Data;

import java.util.List;

@Data
public class TreeResponse {
    private String title;
    private String nodeCode;
    private Integer level;
    private List<Children> children;


    @Data
    public static class Children{
        private String title;
        private Integer level;
        private String nodeCode;
        private List<Children> children;
    }

}
