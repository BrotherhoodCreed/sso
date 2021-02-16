package com.promotion.product.dao.mysql;

import com.promotion.product.dao.dataobject.PromotionMapperDo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PromotionMapperDao {

    @Select("select  \n" +
            "id,\n" +
            "promotion_base_info as 'promotionBaseInfoId',\n" +
            "area,\n" +
            "city,\n" +
            "restaurant_code as 'restaurantCode',\n" +
            "restaurant_name as 'restaurantName',\n" +
            "deleted\n" +
            "from  tb_promotion_mapper where deleted=0 and promotion_base_info= #{promotionBaseInfo}")
    List<PromotionMapperDo> selectByPromotionBaseInfoId(Long promotionBaseInfoId);

    @Insert(" insert into tb_promotion_mapper(\n" +
            " `activity_code`,\n" +
            " `area`,\n" +
            " `city`,\n" +
            " `restaurant_code`\n" +
            " `restaurant_name`\n" +
            " )\n" +
            " values(" +
            "#{item.activityCode}," +
            "#{item.area}," +
            "#{item.city}," +
            "#{item.restaurantCode}," +
            "#{item.restaurantName}," +
            ")")
    Integer insert(@Param("item")PromotionMapperDo promotionMapperDo);

}
