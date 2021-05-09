package com.promotion.product.dao.mysql;


import com.promotion.product.dao.dataobject.*;
import com.promotion.product.entity.ExeclDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PromotionBaseInfoDao {

    @Update("update tb_promotion_base_info set deleted=1 where id=#{id}")
    Integer deletePromotionBaseById(@Param("id") Long id);

    @Select("select count(1) as count from tb_promotion_base_info where deleted=0  ")
    int selectCount();


    @Select("select \n" +
            "id,\n" +
            "activity_code as 'activityCode',\n" +
            "activity_type as 'activityType',\n" +
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
            "updated_user as 'updatedUser',\n" +
            "usage_start_time as 'usageStartTime',\n" +
            "usage_end_time as 'usageEndTime',\n" +
            "contract_amount as 'contractAmount',\n" +
            "prepayment_amount as 'prepaymentAmount',\n" +
            "wage as 'wage',\n" +
            "advertising_fee as 'advertisingFee',\n" +
            "test_fee as 'testFee',\n" +
            "coupon_fee as 'couponFee',\n" +
            "coupon_effective_time as 'couponEffectiveTime',\n" +
            "discount_fee as 'discountFee',\n" +
            "bill_user_name as 'billUserName',\n" +
            "bill_account_number as 'billAccountNumber'\n" +
            "from tb_promotion_base_info  \n" +
            "where deleted=0   AND   activity_code =#{activityCode}")
    PromotionBaseInfoDo selectOneData(@Param("activityCode") String activityCode);



    @Select("select \n" +
            "id,\n" +
            "activity_code as 'activityCode',\n" +
            "activity_type as 'activityType',\n" +
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
            "updated_user as 'updatedUser',\n" +
            "usage_start_time as 'usageStartTime',\n" +
            "usage_end_time as 'usageEndTime',\n" +
            "contract_amount as 'contractAmount',\n" +
            "prepayment_amount as 'prepaymentAmount',\n" +
            "wage as 'wage',\n" +
            "advertising_fee as 'advertisingFee',\n" +
            "test_fee as 'testFee',\n" +
            "coupon_fee as 'couponFee',\n" +
            "coupon_effective_time as 'couponEffectiveTime',\n" +
            "discount_fee as 'discountFee'\n" +
            "from tb_promotion_base_info  \n" +
            "where deleted=0   AND    " +
            "activity_code in <foreach item='item' index='index' collection='aivityCode' open='(' separator=',' close=')'> " +
            "   #{item}" +
            "</foreach>")
    List<PromotionBaseInfoDo> selectByAivityCodes(@Param("aivityCode") List<String>  aivityCode);



    @Insert(" insert into tb_promotion_base_info(\n" +
            " activity_code,\n" +
            " activity_type,\n" +
            " sales_start_time,\n" +
            " sales_end_time,\n" +
            " amount,\n" +
            " bill_cycle,\n" +
            " description,\n" +
            " introduction,\n" +
            " channel,\n" +
            " the_way,\n" +
            " shared_activity,\n" +
            " selling_price,\n" +
            " bill_price,\n" +
            " handling_fee,\n" +
            " tax_rate,\n" +
            " other,\n" +
            " submit,\n" +
            " type,\n" +
            " created_time,\n" +
            " created_user,\n" +
            " updated_time,\n" +
            " updated_user,\n" +
            " usage_start_time,\n" +
            " usage_end_time,\n" +
            " contract_amount,\n" +
            " prepayment_amount,\n" +
            " wage,\n" +
            " advertising_fee,\n" +
            " test_fee,\n" +
            " coupon_fee,\n" +
            " coupon_effective_time,\n" +
            " discount_fee,\n" +
            " bill_user_name,\n" +
            " bill_account_number\n" +
            " )\n" +
            " values(" +
            "#{item.activityCode}," +
            "#{item.activityType}," +
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
            "#{item.usageStartTime}," +
            "#{item.usageEndTime}," +
            "#{item.contractAmount}," +
            "#{item.prepaymentAmount}," +
            "#{item.wage}," +
            "#{item.advertisingFee}," +
            "#{item.testFee}," +
            "#{item.couponFee}," +
            "#{item.couponEffectiveTime}," +
            "#{item.discountFee}," +
            "#{item.billUserName}," +
            "#{item.billAccountNumber}" +
            ")")
    Integer insert(@Param("item")PromotionBaseInfoDo promotionBaseInfoDo);



    Integer update(PromotionBaseInfoDo requery);

    Integer querySerialNumber();

    List<QueryPromotionListDo> queryPromotionList(QueryPromotionListRequest request);
    List<ExeclDto> exportExcel(@Param("list") List<String> list);
}
