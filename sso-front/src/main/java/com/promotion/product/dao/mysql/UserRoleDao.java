package com.promotion.product.dao.mysql;

import com.promotion.product.dao.dataobject.UserRoleDo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRoleDao {

    List<UserRoleDo> queryList(@Param("userMobile") String userMobile,@Param("userName")String userName);

    @Insert("insert into tb_user_role(role_code,user_mobile,user_name) values(#{item.roleCode},#{item.userMobile},#{item.userName})")
    boolean add(UserRoleDo userRoleDo);

    @Update("update tb_user_role set isDeleted=1 where id in   <foreach collection=\"ids\" item=\"value\" open=\"(\" close=\")\" separator=\",\"> #{value} </foreach>")
    boolean delete(@Param("ids") List<Integer> id);

    List<UserRoleDo> queryById(@Param("ids") List<Integer> ids);
}
