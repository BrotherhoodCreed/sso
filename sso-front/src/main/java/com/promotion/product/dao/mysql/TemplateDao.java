package com.promotion.product.dao.mysql;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface TemplateDao {

    @Select("SELECT  \n" +
            "  id ,\n" +
            "  form_id as formId ,\n" +
            "  form_val as formVal,\n" +
            "  deleted,\n" +
            "  remarks,\n" +
            "  template_id as templateId\n" +
            "FROM `tb_template`")
    TemplateDao queryTemplate();

}
