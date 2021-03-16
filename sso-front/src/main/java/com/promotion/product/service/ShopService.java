package com.promotion.product.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.promotion.product.dao.dataobject.PromotionMapperDo;
import com.promotion.product.dao.dataobject.ShopDo;
import com.promotion.product.dao.mysql.PromotionMapperDao;
import com.promotion.product.dao.mysql2.YuKuDao;
import com.promotion.product.entity.TreeResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;


@Service
public class ShopService {
    @Autowired
    private PromotionMapperDao promotionMapperDao;

    @Autowired
    private YuKuDao yuKuDao;

    @Autowired
    Cache<String, Object> caffeineCache;

    public  List<TreeResponse> queryTree(String activityCode,String shopName){
        List<TreeResponse> treeResponseList =new ArrayList<>();
        List<PromotionMapperDo> promotionMapperDos =new ArrayList<>();
        List<String> shopCode=new ArrayList<>();
        if(StringUtils.isNotEmpty(activityCode)){
            promotionMapperDos= promotionMapperDao.selectByActivityCode(activityCode);
            shopCode= promotionMapperDos.stream().map(item->item.getRestaurantCode()).collect(Collectors.toList());
        }

        List<ShopDo> shopDoList =yuKuDao.selectShop();
        if(StringUtils.isNotBlank(shopName)){
            shopDoList.removeIf(item->!item.getStnm().contains(shopName));
        }
        if(CollectionUtils.isEmpty(shopDoList)){
            return treeResponseList;
        }

        Multimap<String,Multimap<String, TreeResponse>> multimap = ArrayListMultimap.create();
        Multimap<String, TreeResponse> childrenMultimap=ArrayListMultimap.create();
        for (ShopDo shopDo : shopDoList) {
            TreeResponse children =new TreeResponse();
            if(CollectionUtils.isNotEmpty(shopCode)){
               Boolean isAny= shopCode.stream().filter(item-> StringUtils.equals(shopDo.getStcd(), item)).findAny().isPresent();
               children.setChecked(isAny);
            }
            children.setLevel(3);
            children.setTitle(shopDo.getStnm());
            children.setId(shopDo.getStcd());
            childrenMultimap.put(shopDo.getCity(),children);
            multimap.put(shopDo.getAm(),childrenMultimap);
        }

        Iterator<Map.Entry<String,Multimap<String, TreeResponse>>>iterator=multimap.entries().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Multimap<String, TreeResponse>> entry= iterator.next();
            TreeResponse treeResponse =treeResponse(entry.getKey(),1);

            Multimap<String, TreeResponse> multimap2=  entry.getValue();
            Iterator<Map.Entry<String, TreeResponse>>iterator2=multimap2.entries().iterator();
            while (iterator2.hasNext()){
                Map.Entry<String, TreeResponse> entry2= iterator2.next();
                TreeResponse treeResponse2 =treeResponse(entry2.getKey(),2);
                //第三层
                treeResponse.getChildren().add(entry2.getValue());
                //第二层
                treeResponse.getChildren().add(treeResponse2);

            }
            treeResponseList.add(treeResponse);
        }


        return treeResponseList;

    }
    private  TreeResponse treeResponse(String key ,Integer level){
        TreeResponse treeResponse =new TreeResponse();
        treeResponse.setLevel(level);
        treeResponse.setTitle(key);
        treeResponse.setId(key);
        return treeResponse;
    }

    public String queryShopAreaId( String restaurantCode){
        return yuKuDao.selectShopAreaId(restaurantCode);
    }

}
