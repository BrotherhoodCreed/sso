package com.promotion.product.controller;

import com.mchange.lang.LongUtils;
import com.promotion.product.dao.dataobject.*;
import com.promotion.product.entity.BaseEntityResponse;
import com.promotion.product.entity.BasePageResponse;
import com.promotion.product.service.DictionarySerivce;
import com.promotion.product.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("PromotionController")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private DictionarySerivce dictionarySerivce;



    /**
     * 根据id查询促销基本信息
     */
    @PostMapping("queryPromotionBaseInfo")
    public BaseEntityResponse<PromotionBaseInfoDo> queryPromotionBaseInfo(Long id) {
        BaseEntityResponse<PromotionBaseInfoDo> response =BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            if(Objects.isNull(id)){
                throw  new Exception("参数为空");
            }
            response.setData(promotionService.queryPromotionBaseInfo(id));
        }
        catch (Exception e){
            response = BaseEntityResponse.failure(BaseEntityResponse.class);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * 根据id查询促销基本信息
     */
    @PostMapping("savePromotionBaseInfo")
    public BaseEntityResponse<Boolean> savePromotionBaseInfo(SavePromotionBaseInfoRequery requery) {
        BaseEntityResponse<Boolean> response =BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            if(Objects.isNull(requery.getPromotionBaseInfoDo())){
                throw  new Exception("参数为空");
            }
            if(CollectionUtils.isEmpty(requery.getPromotionMapperDo())){
                throw  new Exception("参数为空");
            }
            response.setData(promotionService.savePromotionBaseInfo(requery));
        }
        catch (Exception e){
            response = BaseEntityResponse.failure(BaseEntityResponse.class);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * 根据id查询促销基本信息
     */
    @PostMapping("updatePromotionBaseInfo")
    public BaseEntityResponse<Boolean> updatePromotionBaseInfo(UpdatePromotionBaseInfoRequery requery) {
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

    @PostMapping("queryPromotionList")
    public BasePageResponse<queryPromotionListRespone> queryPromotionList(queryPromotionListRequest request){
        BasePageResponse<queryPromotionListRespone> response=BasePageResponse.success(BasePageResponse.class);
        try {
            response=promotionService.queryPromotionList(request);
        }
        catch (Exception e){
            response = BasePageResponse.failure(BasePageResponse.class);
            response.setMessage(e.getMessage());
        }
        return response;

    }


    /**
     * 根据类型查询下拉框数据
     */
    @PostMapping("queryDictionary")
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

}
