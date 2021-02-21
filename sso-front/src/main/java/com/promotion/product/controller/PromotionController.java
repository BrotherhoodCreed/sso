package com.promotion.product.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.promotion.product.common.ExcelUtils;
import com.promotion.product.dao.dataobject.*;
import com.promotion.product.entity.*;
import com.promotion.product.service.DictionarySerivce;
import com.promotion.product.service.PromotionMapperSeriver;
import com.promotion.product.service.PromotionService;
import com.promotion.product.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("PromotionController")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private DictionarySerivce dictionarySerivce;

    @Autowired
    private PromotionMapperSeriver promotionMapperSeriver;

    @Autowired
    private ShopService shopService;


    /**
     * 根据id查询促销基本信息
     */
    @RequestMapping("queryPromotionBaseInfo")
    @ResponseBody
    public BaseEntityResponse<PromotionBaseInfoRespone> queryPromotionBaseInfo(String  activityCode) {
        BaseEntityResponse<PromotionBaseInfoRespone> response =BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            if(StringUtils.isEmpty(activityCode)){
                throw  new Exception("参数为空");
            }
            response.setData(promotionService.queryPromotionBaseInfo(activityCode));
        }
        catch (Exception e){
            response = BaseEntityResponse.failure(BaseEntityResponse.class);
            log.error("queryPromotionBaseInfo 接口异常",e);
        }
        return response;
    }

    /**
     * 保存促销基本信息
     */
//    @PostMapping("savePromotionBaseInfo")
    @RequestMapping("savePromotionBaseInfo")
    @ResponseBody
    public BaseEntityResponse<SavePromotionBaseInfoRespone> savePromotionBaseInfo(@RequestBody  SavePromotionBaseInfoRequery request) {
        BaseEntityResponse<SavePromotionBaseInfoRespone> response =BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            response.setData(promotionService.savePromotionBaseInfo(request,request.getPromotionMapperDo()));
        }
        catch (Exception e){
            e.printStackTrace();
            response = BaseEntityResponse.failure(BaseEntityResponse.class);
            log.error("queryPromotionBaseInfo 接口异常",e);
        }
        return response;
    }

    /**
     * 保存促销门店
     */
    @RequestMapping("savePromotionMapperInfo")
    @ResponseBody
    public BaseEntityResponse<Boolean> savePromotionMapperInfo(@RequestBody  savePromotionMapperInfoRequest request){
        BaseEntityResponse<Boolean> response =BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            response.setData(promotionService.savePromotionMapperInfo(request));
        }
        catch (Exception e){
            e.printStackTrace();
            response = BaseEntityResponse.failure(BaseEntityResponse.class);
            response.setMessage(e.getMessage());
        }
        return response;
    }



    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletResponse response,List<Long> id)  throws IOException {
        List<ExeclRespone> resultList = new ArrayList<>();
        ExeclRespone execlRespone = new ExeclRespone();
        execlRespone.setArea("aaa");
        //查询数据
        resultList.add(execlRespone);

        long t1 = System.currentTimeMillis();
        ExcelUtils.writeExcel(response, resultList, ExeclRespone.class);
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("write over! cost:%sms", (t2 - t1)));
    }

    /**
     * 根据activityCode修改活动基本信息
     */
    @RequestMapping("updatePromotionBaseInfo")
    @ResponseBody
    public BaseEntityResponse<Boolean> updatePromotionBaseInfo(@RequestBody UpdatePromotionBaseInfoRequery requery) {
        BaseEntityResponse<Boolean> response =BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            if(Objects.isNull(requery.getPromotionBaseInfoDo())){
                throw  new Exception("参数为空");
            }
            response.setData(promotionService.updatePromotionBaseInfo(requery));
        }
        catch (Exception e){
            response = BaseEntityResponse.failure(BaseEntityResponse.class);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping("queryPromotionList")
    @ResponseBody
    public  BasePageResponse<QueryPromotionListRespone> queryPromotionList(QueryPromotionListRequest request){
        BasePageResponse<QueryPromotionListRespone> response=BasePageResponse.success(BasePageResponse.class);
        try {
            response=promotionService.queryPromotionList(request);
        }
        catch (Exception e){
            e.printStackTrace();
            response = BasePageResponse.failure(BasePageResponse.class);
            response.setMessage(e.getMessage());
        }
        return response;

    }

    /**
     * 删除活动与
     */
    @RequestMapping("deletePromotion")
    @ResponseBody
    public BaseEntityResponse<Boolean> deletePromotionBaseById(List<Long> id){
        BaseEntityResponse<Boolean> response=BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            response.setData(promotionService.deletePromotionBaseById(id));
        }
        catch (Exception e){
            response = BaseEntityResponse.failure(BaseEntityResponse.class);
            response.setMessage(e.getMessage());
        }
        return response;

    }

    /**
     * 删除活动与门店映射关系
     */
    @RequestMapping("deletePromotionRel")
    @ResponseBody
    public BaseEntityResponse<Boolean> deletePromotionMapper(Long id){
        BaseEntityResponse<Boolean> response=BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            response.setData(promotionService.deletePromotionById(id));
        }
        catch (Exception e){
            e.printStackTrace();
            response = BaseEntityResponse.failure(BaseEntityResponse.class);
            response.setMessage(e.getMessage());
        }
        return response;

    }


    /**
     * 根据类型查询下拉框数据
     */
    @RequestMapping("queryDictionary")
    @ResponseBody
    public BaseEntityResponse<List<DictionaryDo>> queryDictionary(String descriptionType){
        BaseEntityResponse<List<DictionaryDo>> response =BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            if(StringUtils.isEmpty(descriptionType)){
                throw  new Exception("参数为空");
            }
            response.setData(dictionarySerivce.queryDictionary(descriptionType));
        }catch (Exception e){
            response = BaseEntityResponse.failure(BaseEntityResponse.class);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * 查询树形结构
     */
    @RequestMapping("queryTree")
    @ResponseBody
    private BaseEntityResponse<List<TreeResponse>> queryTree(String  activityCode){
        BaseEntityResponse<List<TreeResponse>> response =BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            response.setData(shopService.queryTree(activityCode));
        }catch (Exception e){
            response = BaseEntityResponse.failure(BaseEntityResponse.class);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping("activeList")
    @ResponseBody
    private BaseEntityResponse<List<PromotionMapperDo>> queryShopBind(String  activityCode){
        BaseEntityResponse<List<PromotionMapperDo>> response =BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            response.setData(promotionMapperSeriver.queryByPromotionBaseInfoId(activityCode));
        }catch (Exception e){
            response = BaseEntityResponse.failure(BaseEntityResponse.class);
            response.setMessage(e.getMessage());
        }
        return response;
    }


}
