package com.promotion.product.controller;

import com.promotion.product.dao.dataobject.DictionaryDo;
import com.promotion.product.entity.BaseEntityResponse;
import com.promotion.product.service.DictionarySerivce;
import com.promotion.product.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("PromotionController")
public class PromotionController {

    @Autowired
    private PromotionService promotion;

    @Autowired
    private DictionarySerivce dictionarySerivce;


    @PostMapping("a")
    public BaseEntityResponse<Boolean> a() {
        BaseEntityResponse<Boolean> baseEntityResponse =BaseEntityResponse.success(BaseEntityResponse.class);
        return baseEntityResponse;
    }

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
