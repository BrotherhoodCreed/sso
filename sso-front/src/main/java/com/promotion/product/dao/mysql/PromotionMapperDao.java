package com.promotion.product.dao.mysql;

import com.promotion.product.dao.dataobject.PromotionMapperDo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PromotionMapperDao {

    @Update("update tb_promotion_mapper set deleted=1 where id=#{id}")
    Integer deletePromotionById(@Param("id")Long id);

    @Select("select id,activity_code as 'activityCode',area,city,restaurant_code as 'restaurantCode',restaurant_name as 'restaurantName',deleted from  tb_promotion_mapper where deleted=0 and activity_code= #{activityCode}")
    List<PromotionMapperDo> selectByActivityCode(@Param("activityCode")  String activityCode);

    @Insert(" insert into tb_promotion_mapper(\n" +
            " `activity_code`,\n" +
            " `area`,\n" +
            " `city`,\n" +
            " `restaurant_code`,\n" +
            " `restaurant_name`\n" +
            " )\n" +
            " values(" +
            "#{item.activityCode}," +
            "#{item.area}," +
            "#{item.city}," +
            "#{item.restaurantCode}," +
            "#{item.restaurantName}" +
            ")")
    Integer insert(@Param("item")PromotionMapperDo promotionMapperDo);

}
