package com.promotion.product.dao.mysql;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface PromotionBaseInfoDao {
    @Select("select count(1) as count from tb_promotion_base_info where deleted=0 ")
    int selectCount();



}
