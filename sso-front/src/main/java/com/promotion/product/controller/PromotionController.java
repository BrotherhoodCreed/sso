package com.promotion.product.controller;

import com.promotion.product.common.ExcelUtils;
import com.promotion.product.dao.dataobject.*;
import com.promotion.product.entity.BaseEntityResponse;
import com.promotion.product.entity.BasePageResponse;
import com.promotion.product.entity.ExeclRespone;
import com.promotion.product.service.DictionarySerivce;
import com.promotion.product.service.PromotionService;
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
    @RequestMapping("queryPromotionBaseInfo")
    @ResponseBody
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
     * 保存促销基本信息
     */
//    @PostMapping("savePromotionBaseInfo")
    @RequestMapping("savePromotionBaseInfo")
    @ResponseBody
    public BaseEntityResponse<Boolean> savePromotionBaseInfo(@RequestBody  SavePromotionBaseInfoRequery request) {
        BaseEntityResponse<Boolean> response =BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            if(Objects.isNull(request.getPromotionBaseInfoDo())){
                throw  new Exception("参数为空");
            }
            if(CollectionUtils.isEmpty(request.getPromotionMapperDo())){
                throw  new Exception("参数为空");
            }
            response.setData(promotionService.savePromotionBaseInfo(request));
        }
        catch (Exception e){
            response = BaseEntityResponse.failure(BaseEntityResponse.class);
            response.setMessage(e.getMessage());
        }
        return response;
    }



    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletResponse response)  throws IOException {
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
     * 修改id查询促销基本信息
     */
    @RequestMapping("updatePromotionBaseInfo")
    @ResponseBody
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

    @RequestMapping("queryPromotionList")
    @ResponseBody
    public String queryPromotionList(queryPromotionListRequest request){
        BasePageResponse<queryPromotionListRespone> response=BasePageResponse.success(BasePageResponse.class);
        try {
//            response=promotionService.queryPromotionList(request);
        }
        catch (Exception e){
            response = BasePageResponse.failure(BasePageResponse.class);
            response.setMessage(e.getMessage());
        }
        return "{\n" +
                "\t\"code\": 10000,\n" +
                "\t\"message\": \"\",\n" +
                "\t\"count\": 1000,\n" +
                "\t\"data\": [{\n" +
                "\t\t\"id\": 10000,\n" +
                "\t\t\"restaurantCode\": \"user-0\",\n" +
                "\t\t\"activityCode\": \"女\",\n" +
                "\t\t\"activityType\": \"城市-0\",\n" +
                "\t\t\"salesStartTime\": \"签名-0\",\n" +
                "\t\t\"experience\": 255,\n" +
                "\t\t\"logins\": 24,\n" +
                "\t\t\"wealth\": 82830700,\n" +
                "\t\t\"classify\": \"作家\",\n" +
                "\t\t\"score\": 57\n" +
                "\t}, {\n" +
                "\t\t\"id\": 10001,\n" +
                "\t\t\"restaurantCode\": \"user-1\",\n" +
                "\t\t\"activityCode\": \"男\",\n" +
                "\t\t\"activityType\": \"城市-1\",\n" +
                "\t\t\"salesStartTime\": \"签名-1\",\n" +
                "\t\t\"experience\": 884,\n" +
                "\t\t\"logins\": 58,\n" +
                "\t\t\"wealth\": 64928690,\n" +
                "\t\t\"classify\": \"词人\",\n" +
                "\t\t\"score\": 27\n" +
                "\t}, {\n" +
                "\t\t\"id\": 10002,\n" +
                "\t\t\"restaurantCode\": \"user-2\",\n" +
                "\t\t\"activityCode\": \"女\",\n" +
                "\t\t\"activityType\": \"城市-2\",\n" +
                "\t\t\"salesStartTime\": \"签名-2\",\n" +
                "\t\t\"experience\": 650,\n" +
                "\t\t\"logins\": 77,\n" +
                "\t\t\"wealth\": 6298078,\n" +
                "\t\t\"classify\": \"酱油\",\n" +
                "\t\t\"score\": 31\n" +
                "\t}, {\n" +
                "\t\t\"id\": 10003,\n" +
                "\t\t\"restaurantCode\": \"user-3\",\n" +
                "\t\t\"activityCode\": \"女\",\n" +
                "\t\t\"activityType\": \"城市-3\",\n" +
                "\t\t\"salesStartTime\": \"签名-3\",\n" +
                "\t\t\"experience\": 362,\n" +
                "\t\t\"logins\": 157,\n" +
                "\t\t\"wealth\": 37117017,\n" +
                "\t\t\"classify\": \"诗人\",\n" +
                "\t\t\"score\": 68\n" +
                "\t}, {\n" +
                "\t\t\"id\": 10004,\n" +
                "\t\t\"restaurantCode\": \"user-4\",\n" +
                "\t\t\"activityCode\": \"男\",\n" +
                "\t\t\"activityType\": \"城市-4\",\n" +
                "\t\t\"salesStartTime\": \"签名-4\",\n" +
                "\t\t\"experience\": 807,\n" +
                "\t\t\"logins\": 51,\n" +
                "\t\t\"wealth\": 76263262,\n" +
                "\t\t\"classify\": \"作家\",\n" +
                "\t\t\"score\": 6\n" +
                "\t}, {\n" +
                "\t\t\"id\": 10005,\n" +
                "\t\t\"restaurantCode\": \"user-5\",\n" +
                "\t\t\"activityCode\": \"女\",\n" +
                "\t\t\"activityType\": \"城市-5\",\n" +
                "\t\t\"salesStartTime\": \"签名-5\",\n" +
                "\t\t\"experience\": 173,\n" +
                "\t\t\"logins\": 68,\n" +
                "\t\t\"wealth\": 60344147,\n" +
                "\t\t\"classify\": \"作家\",\n" +
                "\t\t\"score\": 87\n" +
                "\t}]\n" +
                "}";

    }

    /**
     * 删除活动与
     */
    @RequestMapping("deletePromotion")
    @ResponseBody
    public BaseEntityResponse<Boolean> deletePromotionBaseById(Long id){
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
    @RequestMapping("deletePromotionMapper")
    @ResponseBody
    public BaseEntityResponse<Boolean> deletePromotionMapper(Long id){
        BaseEntityResponse<Boolean> response=BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            response.setData(promotionService.deletePromotionById(id));
        }
        catch (Exception e){
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

}
