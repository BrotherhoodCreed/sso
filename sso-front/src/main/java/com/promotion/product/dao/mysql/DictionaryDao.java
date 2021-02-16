package com.promotion.product.dao.mysql;

import com.promotion.product.dao.dataobject.DictionaryDo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DictionaryDao {

    @Select("select id,description,description_code as 'descriptionCode',description_type  as 'descriptionType',deleted  from tb_dictionary where deleted=0 AND description_type = #{description_type}")
    List<DictionaryDo> select(@Param("description_type") String descriptionType);




}
