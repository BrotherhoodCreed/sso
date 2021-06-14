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
    boolean add(@Param("item") UserRoleDo userRoleDo);

    @Update("update tb_user_role set is_deleted=1 where id in   <foreach collection=\"ids\" item=\"value\" open=\"(\" close=\")\" separator=\",\"> #{value} </foreach>")
    boolean delete(@Param("ids") List<Integer> id);

    List<UserRoleDo> queryByIds(@Param("id") Integer id);

    UserRoleDo queryById(@Param("id")Integer id);

    @Update("update tb_user_role set role_code=#{roleCode}  where id =#{id} ")
    boolean updateById(@Param("id")int id,@Param("roleCode") String roleCode);
}
