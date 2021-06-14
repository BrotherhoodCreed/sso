package com.promotion.product.dao.mysql2;

import com.promotion.product.dao.dataobject.FineUserDo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FineUserDao {
   @Select("SELECT * from finedb.fine_user WHERE MOBILE=#{mobile} limit 1")
   FineUserDo query(@Param("mobile")String mobile);

   @Select("SELECT * from finedb.fine_user ")
   List<FineUserDo> queryList();
}
