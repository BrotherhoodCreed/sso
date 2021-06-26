package com.promotion.product.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.promotion.product.dao.dataobject.*;
import com.promotion.product.dao.mysql.PromotionMapperDao;
import com.promotion.product.dao.mysql2.FineUserDao;
import com.promotion.product.dao.mysql2.UserStoreDao;
import com.promotion.product.dao.mysql2.YuKuDao;
import com.promotion.product.entity.BasePageResponse;
import com.promotion.product.entity.BizErrorEnum;
import com.promotion.product.entity.TreeResponse;
import com.promotion.product.entity.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ShopService {
    @Autowired
    private PromotionMapperDao promotionMapperDao;

    @Autowired
    private YuKuDao yuKuDao;
    @Autowired
    private FineUserDao fineUserDao;

    @Autowired
    private UserStoreDao userStoreDao;

    @Autowired
    Cache<String, Object> caffeineCache;

    public  List<TreeResponse> queryTree(String activityCode, String shopName, UserDao userDao){
        //todo 根据钉钉手机号查询用户信息，用户信息不存在报错提示联系it ，用户信息存在查询数据权限
        List<TreeResponse> treeResponseList =new ArrayList<>();
        List<PromotionMapperDo> promotionMapperDos =new ArrayList<>();
        List<String> shopCode=new ArrayList<>();
        if(StringUtils.isNotEmpty(activityCode)){
            promotionMapperDos= promotionMapperDao.selectByActivityCode(activityCode);
            shopCode= promotionMapperDos.stream().map(item->item.getRestaurantCode()).collect(Collectors.toList());
        }

        List<ShopDo> shopDoList =yuKuDao.selectShop();
        Boolean sentinel =false;
        if(StringUtils.isNotBlank(shopName)){
            shopDoList.removeIf(item->!item.getStnm().contains(shopName));
            sentinel=true;
        }
        if(CollectionUtils.isEmpty(shopDoList)){
            return treeResponseList;
        }
        Map<String,Map<String,Collection<TreeResponse>>> multimap = new HashMap<>();
        HashMap<String,Map<String,List<TreeResponse>>> result = new HashMap<>();

        Multimap<String, String> mapper=ArrayListMultimap.create();

        String mobile = userDao.getMobile();
        if (null == mobile){
            log.info("查询活动列表 | 用户手机号为空[{}]",userDao);
            return treeResponseList;

        }
        FineUserDo fineUserDo =  fineUserDao.query(mobile);
        if (null == fineUserDo){
            log.info("查询活动列表 | 用户手机号未找到对应数据",mobile);
            return treeResponseList;
        }
        UserStoreDo userStoreDo = userStoreDao.query(fineUserDo.getUserName());
        if (null == userStoreDo){
            log.info("查询活动列表 | 用户未找到对应StCd",fineUserDo.getUserName());
            return treeResponseList;
        }
        List<String> stcds = Arrays.asList(userStoreDo.getStCd().split(","));
        Iterator<ShopDo> shop_iterator = shopDoList.iterator();
        while (shop_iterator.hasNext()){
            ShopDo shopDo = shop_iterator.next();
            if (!stcds.contains(shopDo.getStcd())){
                continue;
            }
            //  A    A1  01
            //  A    A1  02
            TreeResponse children =new TreeResponse();
            if(CollectionUtils.isNotEmpty(shopCode)){
                Boolean isAny= shopCode.stream().filter(item-> StringUtils.equals(shopDo.getStcd(), item)).findAny().isPresent();
                children.setChecked(isAny);
            }
            children.setLevel(3);
            children.setTitle(shopDo.getStnm());
            children.setId(shopDo.getStcd());
            children.setAm(shopDo.getAm());
            children.setCity(shopDo.getCity());
            children.setAccountnumber(shopDo.getAccountnumber());
            children.setAmcd(shopDo.getAmcd());
            children.setDepositbank(shopDo.getDepositbank());
            children.setUid(shopDo.getUid());
            if(sentinel){
                children.setSpread(true);
            }
            if (result.containsKey(shopDo.getAm())){
                Map<String,List<TreeResponse>> cityMap = result.get(shopDo.getAm());
                if (cityMap.containsKey(shopDo.getCity())){
                    cityMap.get(shopDo.getCity()).add(children);
                }else {
                    List<TreeResponse> list = new ArrayList<>();
                    list.add(children);
                    cityMap.put(shopDo.getCity(),list);
                }
            }else {
                Map<String,List<TreeResponse>> map = new HashMap<>();
                List<TreeResponse> list = new ArrayList<>();
                list.add(children);
                map.put(shopDo.getCity(),list);
                result.put(shopDo.getAm(),map);
            }
        }


        Iterator<Map.Entry<String, Map<String,List<TreeResponse>>>> iterator=result.entrySet().iterator();
        Map<String,TreeResponse> map =new HashMap<>();
        while (iterator.hasNext()){
            Map.Entry<String, Map<String, List<TreeResponse>>> entry= iterator.next();
            TreeResponse treeResponse =treeResponse(entry.getKey(),1,sentinel);
            Map<String, List<TreeResponse>> multimap2=  entry.getValue();
            Iterator<Map.Entry<String, List<TreeResponse>>>iterator2=multimap2.entrySet().iterator();
            if(map.get(entry.getKey())!=null){
                continue;
            }
            List<TreeResponse> treeResponseList1 =new ArrayList<>();
            while (iterator2.hasNext()){
                Map.Entry<String, List<TreeResponse>> entry2= iterator2.next();
                TreeResponse treeResponse2 =treeResponse(entry2.getKey(),2,sentinel);
                Map<String,TreeResponse> map2 =new HashMap<>();
                if(map2.get(entry2.getKey())==null){
                    map2.put(entry2.getKey(),treeResponse2);
                    Collection<TreeResponse> level3=entry2.getValue();
                    treeResponse2.getChildren().addAll(level3);
                    treeResponseList1.add(treeResponse2);
                }
            }
            if (null != treeResponseList1 && treeResponseList1.size()>0
                    && null != treeResponseList1.get(0).getChildren() && treeResponseList1.get(0).getChildren().size()>0){
                treeResponse.setAmcd(treeResponseList1.get(0).getChildren().get(0).getAmcd());
            }
            treeResponse.getChildren().addAll(treeResponseList1);
            treeResponseList1=new ArrayList<>();
            treeResponseList.add(treeResponse);
        }
        Collections.sort(treeResponseList, new Comparator<TreeResponse>() {
            @Override
            public int compare(TreeResponse o1, TreeResponse o2) {
                try {
                    int sort1 = 0;
                    int sort2 = 0;
                    if (null != o1.getAmcd()){
                        sort1 = Integer.valueOf(o1.getAmcd());
                    }
                    if (null != o2.getAmcd()){
                        sort2 = Integer.valueOf(o2.getAmcd());
                    }
                    return sort1-sort2;
                }catch (Exception e){
                    log.error("排序异常[{}]",e);
                    return 0;
                }

            }
        });

        return treeResponseList;

    }
    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    private  TreeResponse treeResponse(String key ,Integer level,Boolean sentinel){
        TreeResponse treeResponse =new TreeResponse();
        treeResponse.setLevel(level);
        treeResponse.setTitle(key);
        treeResponse.setId(key);
        if(sentinel){
            treeResponse.setSpread(true);
        }
        return treeResponse;
    }

    public String queryShopAreaId( String restaurantCode){
        return yuKuDao.selectShopAreaId(restaurantCode);
    }

}
