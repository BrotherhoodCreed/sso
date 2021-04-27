package com.promotion.product.dao.mysql2;

import com.promotion.product.dao.dataobject.FineUserDo;
import com.promotion.product.dao.dataobject.UserStoreDo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface UserStoreDao {
    @Select("SELECT * from finebi_dw.user_store WHERE userName=#{userName} LIMIT 1")
    UserStoreDo query(@Param("userName")String userName);
}
