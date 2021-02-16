package com.promotion.product.dao.dataobject;

import lombok.Data;

@Data
public class DictionaryDo {

    private Long id;

    private String description;

    private String descriptionCode;

    private String descriptionType;

    private Boolean deleted;
}
