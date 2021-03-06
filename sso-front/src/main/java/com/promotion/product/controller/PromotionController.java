package com.promotion.product.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.promotion.product.common.ExcelUtils;
import com.promotion.product.config.Constans;
import com.promotion.product.dao.dataobject.*;
import com.promotion.product.entity.*;
import com.promotion.product.service.DictionarySerivce;
import com.promotion.product.service.PromotionMapperSeriver;
import com.promotion.product.service.PromotionService;
import com.promotion.product.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    public BaseEntityResponse<PromotionBaseInfoRespone> queryPromotionBaseInfo(String activityCode, HttpServletRequest request) {
        BaseEntityResponse<PromotionBaseInfoRespone> response = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            if (StringUtils.isEmpty(activityCode)) {
                throw new Exception("参数为空");
            }
            response.setData(promotionService.queryPromotionBaseInfo(activityCode));
        } catch (Exception e) {
            response = BaseEntityResponse.failure(BaseEntityResponse.class);
            log.error("queryPromotionBaseInfo 接口异常", e);
        }
        return response;
    }

    /**
     * 保存促销基本信息
     */
//    @PostMapping("savePromotionBaseInfo")
    @RequestMapping("savePromotionBaseInfo")
    @ResponseBody
    public BaseEntityResponse<SavePromotionBaseInfoRespone> savePromotionBaseInfo(@RequestBody SavePromotionBaseInfoRequery request,HttpServletRequest httpRequest) {
        BaseEntityResponse<SavePromotionBaseInfoRespone> response = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            Object o = httpRequest.getSession().getAttribute(Constans.USER_CONTENT);
            UserDao user = (UserDao)o ;
            request.setCreatedUser(user.getMobile());
            synchronized(this){
                response.setData(promotionService.savePromotionBaseInfo(request, request.getPromotionMapperDo()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = BaseEntityResponse.failure(BaseEntityResponse.class);
            log.error("queryPromotionBaseInfo 接口异常", e);
        }
        return response;
    }

    /**
     * 保存促销门店
     */
    @RequestMapping("savePromotionMapperInfo")
    @ResponseBody
    public BaseEntityResponse<Boolean> savePromotionMapperInfo(@RequestBody SavePromotionMapperInfoRequest request) {
        BaseEntityResponse<Boolean> response = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            response.setData(promotionService.savePromotionMapperInfo(request));
        } catch (Exception e) {
            e.printStackTrace();
            response = BaseEntityResponse.failure(BaseEntityResponse.class);
            response.setMessage(e.getMessage());
        }
        return response;
    }


    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletResponse response, String codestr) throws IOException {
        //查询数据
        List<String> codes = JSON.parseArray(codestr, String.class);
        List<ExeclRespone> resultList = promotionService.exportExcel(codes);

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
    public BaseEntityResponse<Boolean> updatePromotionBaseInfo(@RequestBody UpdatePromotionBaseInfoRequery requery,HttpServletRequest httpRequest) {
        BaseEntityResponse<Boolean> response = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            if (Objects.isNull(requery.getPromotionBaseInfoDo())) {
                throw new Exception("参数为空");
            }
            Object o = httpRequest.getSession().getAttribute(Constans.USER_CONTENT);
            UserDao user = (UserDao)o ;
            requery.getPromotionBaseInfoDo().setUpdatedUser(user.getMobile());
            response.setData(promotionService.updatePromotionBaseInfo(requery));
        } catch (Exception e) {
            response = BaseEntityResponse.failure(BaseEntityResponse.class);
            response.setMessage(e.getMessage());
            log.error("异常", e);
        }
        return response;
    }

    @RequestMapping("queryPromotionList")
    @ResponseBody
    public BasePageResponse<QueryPromotionListRespone> queryPromotionList(HttpServletRequest httpRequest,int pageSize, int pageIndex, String activityCode, String begainTime, String endTime) {
        BasePageResponse<QueryPromotionListRespone> response = BasePageResponse.success(BasePageResponse.class);
        try {
            QueryPromotionListRequest request = new QueryPromotionListRequest();
            request.setPageIndex(pageIndex);
            request.setActivityCode(activityCode);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Object o = httpRequest.getSession().getAttribute(Constans.USER_CONTENT);
            UserDao user = (UserDao)o ;
            if (null == user){
                response.setCode(-1);
                response.setMessage("请联系it,用户信息存在查询数据权限");
                throw new RuntimeException("请联系it,用户信息存在查询数据权限");
            }
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(begainTime)) {
                 Date begain = simpleDateFormat.parse(begainTime);
                 request.setBegainTime(begain);
            }
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(endTime)) {
                Date end = simpleDateFormat.parse(endTime);
                if (endTime.equals(begainTime)) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(end);
                    calendar.add(Calendar.DATE, 1);
                    end = calendar.getTime();
                }
                request.setEndTime(end);
            }
            request.setPageSize(pageSize);

            response = promotionService.queryPromotionList(request,user);
        } catch (Exception e) {
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
    public BaseEntityResponse<Boolean> deletePromotionBaseById(String ids) {
        List<Long> id = JSON.parseArray(ids, Long.class);
        BaseEntityResponse<Boolean> response = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            response.setData(promotionService.deletePromotionBaseById(id));
        } catch (Exception e) {
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
    public BaseEntityResponse<Boolean> deletePromotionMapper(Long id) {
        BaseEntityResponse<Boolean> response = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            response.setData(promotionService.deletePromotionById(id));
        } catch (Exception e) {
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
    public BaseEntityResponse<List<DictionaryDo>> queryDictionary(String descriptionType) {
        BaseEntityResponse<List<DictionaryDo>> response = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            if (StringUtils.isEmpty(descriptionType)) {
                throw new Exception("参数为空");
            }
            response.setData(dictionarySerivce.queryDictionary(descriptionType));
        } catch (Exception e) {
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
    private BaseEntityResponse<List<TreeResponse>> queryTree(HttpServletRequest request,String activityCode, String shopName) {
        BaseEntityResponse<List<TreeResponse>> response = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            Object o = request.getSession().getAttribute(Constans.USER_CONTENT);
            UserDao user = (UserDao)o ;
            if (null == user){
                response.setCode(-1);
                response.setMessage("请联系it,用户信息存在查询数据权限");
                throw new RuntimeException("请联系it,用户信息存在查询数据权限");
            }
            response.setData(shopService.queryTree(activityCode, shopName,user));
        } catch (Exception e) {
            response = BaseEntityResponse.failure(BaseEntityResponse.class);
            response.setMessage(e.getMessage());
        }
        return response;
    }


    /**
     * 保存促销门店
     */
    @RequestMapping("activeList")
    @ResponseBody
    public String activeList() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("name", "我哦SD卡");
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("id", 2);
        jsonObject2.put("name", "ad上线");
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("id", 3);
        jsonObject3.put("name", "撒大声地股份");
        jsonArray.add(jsonObject);
        jsonArray.add(jsonObject2);
        jsonArray.add(jsonObject3);
        return jsonArray.toJSONString();
    }

    @RequestMapping("queryShopBind")
    @ResponseBody
    private BaseEntityResponse<List<PromotionMapperDo>> queryShopBind(String activityCode) {
        BaseEntityResponse<List<PromotionMapperDo>> response = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            response.setData(promotionMapperSeriver.queryByPromotionBaseInfoId(activityCode));
        } catch (Exception e) {
            e.printStackTrace();
            response = BaseEntityResponse.failure(BaseEntityResponse.class);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    private Boolean startProcess(List<String> activityCode) {
        promotionService.startProcess(activityCode);

        return false;
    }

}
