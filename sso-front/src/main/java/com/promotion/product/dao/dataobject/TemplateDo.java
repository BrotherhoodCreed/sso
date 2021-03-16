package com.promotion.product.dao.dataobject;

import lombok.Data;

@Data
public class TemplateDo {
    private Long id;
    private String formId;
    private String formVal;
    private Boolean deleted;
    private String remarks;
    private String templateId;
}
