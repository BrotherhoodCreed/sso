package com.soso.product1.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface TestDao {

    @Select("select count(*) from tb_user")
    int select();
}
