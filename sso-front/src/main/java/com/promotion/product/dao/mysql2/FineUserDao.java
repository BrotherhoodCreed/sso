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

   @Select("SELECT * from finedb.fine_user  where mobile is not null and mobile != ''")
   List<FineUserDo> queryList();

   @Select("SELECT * from finedb.fine_user where  mobile = #{mobile} or userName=#{mobile}")
   List<FineUserDo> queryListWhere(@Param("mobile") String mobile);
}
