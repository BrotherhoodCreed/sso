package com.promotion.product.dao.mysql;


import com.promotion.product.dao.dataobject.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PromotionBaseInfoDao {
    @Select("select count(1) as count from tb_promotion_base_info where deleted=0  ")
    int selectCount();


    @Select("select \n" +
            "id,\n" +
            "activity_code as 'aivityCode',\n" +
            "activity_type as 'aivityType',\n" +
            "sales_start_time as 'salesStartTime',\n" +
            "sales_end_time as 'salesEndTime',\n" +
            "amount as 'amount',\n" +
            "bill_cycle as 'billCycle',\n" +
            "description as 'description',\n" +
            "introduction as 'introduction',\n" +
            "channel as 'channel',\n" +
            "the_way as 'theWay',\n" +
            "shared_activity as 'sharedActivity',\n" +
            "selling_price as 'sellingPrice',\n" +
            "bill_price as 'billPrice',\n" +
            "handling_fee as 'handlingFee',\n" +
            "tax_rate as 'taxRate',\n" +
            "other as 'other',\n" +
            "submit as 'submit',\n" +
            "type as 'type',\n" +
            "created_time as 'createdTime',\n" +
            "created_user as 'createdUser',\n" +
            "updated_time as 'updatedTime',\n" +
            "updated_user as 'updatedUser'\n" +
            "from tb_promotion_base_info  \n" +
            "where deleted=0   AND   id =#{id}")
    PromotionBaseInfoDo selectOneData(@Param("id") Long id);

    @Select("select \n" +
            "id,\n" +
            "activity_code as 'aivityCode',\n" +
            "activity_type as 'aivityType',\n" +
            "sales_start_time as 'salesStartTime',\n" +
            "sales_end_time as 'salesEndTime',\n" +
            "amount as 'amount',\n" +
            "bill_cycle as 'billCycle',\n" +
            "description as 'description',\n" +
            "introduction as 'introduction',\n" +
            "channel as 'channel',\n" +
            "the_way as 'theWay',\n" +
            "shared_activity as 'sharedActivity',\n" +
            "selling_price as 'sellingPrice',\n" +
            "bill_price as 'billPrice',\n" +
            "handling_fee as 'handlingFee',\n" +
            "tax_rate as 'taxRate',\n" +
            "other as 'other',\n" +
            "submit as 'submit',\n" +
            "type as 'type',\n" +
            "created_time as 'createdTime',\n" +
            "created_user as 'createdUser',\n" +
            "updated_time as 'updatedTime',\n" +
            "updated_user as 'updatedUser'\n" +
            "from tb_promotion_base_info  \n" +
            "where deleted=0   AND   activity_code =#{aivityCode}")
    PromotionBaseInfoDo selectByAivityCode(@Param("aivityCode") String  aivityCode);



    @Insert(" insert into tb_promotion_base_info(\n" +
            " 'activity_code',\n" +
            " 'activity_type',\n" +
            " 'sales_start_time',\n" +
            " 'sales_end_time',\n" +
            " 'amount',\n" +
            " 'bill_cycle',\n" +
            " 'description',\n" +
            " 'introduction',\n" +
            " 'channel',\n" +
            " 'the_way',\n" +
            " 'shared_activity',\n" +
            " 'selling_price',\n" +
            " 'bill_price',\n" +
            " 'handling_fee',\n" +
            " 'tax_rate',\n" +
            " 'other',\n" +
            " 'submit',\n" +
            " 'type',\n" +
            " 'created_time',\n" +
            " 'created_user',\n" +
            " 'updated_time',\n" +
            " 'updated_user'\n" +
            " )\n" +
            " values(" +
            "#{item.aivityCode}," +
            "#{item.aivityType}," +
            "#{item.salesStartTime}," +
            "#{item.salesEndTime}," +
            "#{item.amount}," +
            "#{item.billCycle}," +
            "#{item.description}," +
            "#{item.introduction}," +
            "#{item.channel}," +
            "#{item.theWay}," +
            "#{item.sharedActivity}," +
            "#{item.sellingPrice}," +
            "#{item.billPrice}," +
            "#{item.handlingFee}," +
            "#{item.taxRate}," +
            "#{item.other}," +
            "#{item.submit}," +
            "#{item.type}," +
            "#{item.createdTime}," +
            "#{item.createdUser}," +
            "#{item.updatedTime}," +
            "#{item.updatedUser}," +
            ")")
    Integer insert(@Param("item")PromotionBaseInfoDo promotionBaseInfoDo);



    Integer update(UpdatePromotionBaseInfoRequery requery);

    Integer querySerialNumber();

    List<queryPromotionListDo> queryPromotionList(queryPromotionListRequest request);
}
