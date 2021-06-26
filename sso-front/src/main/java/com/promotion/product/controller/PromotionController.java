package com.promotion.product.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.promotion.product.common.ExcelUtils;
import com.promotion.product.config.Constans;
import com.promotion.product.dao.dataobject.*;
import com.promotion.product.entity.*;
import com.promotion.product.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private UserRoleService userRoleService;


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
     * 保存堂食
     */
    @RequestMapping("savePromotionBaseInfoTs")
    @ResponseBody
    public BaseEntityResponse<SavePromotionBaseInfoRespone> savePromotionBaseInfoTs(@RequestBody SavePromotionBaseInfoRequery request,HttpServletRequest httpRequest) {
        BaseEntityResponse<SavePromotionBaseInfoRespone> response = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            Object o = httpRequest.getSession().getAttribute(Constans.USER_CONTENT);
            UserDao user = (UserDao)o ;
            request.setCreatedUser(user.getMobile());
            synchronized(this){
                response.setData(promotionService.savePromotionBaseInfo_ts(request, request.getPromotionMapperDo()));
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

    @RequestMapping(value = "/exportExcelTs", method = RequestMethod.GET)
    public void exportExcelTs(HttpServletResponse response, String codestr) throws IOException {
        //查询数据
        List<String> codes = JSON.parseArray(codestr, String.class);
        List<ExeclResponeTs> resultList = promotionService.exportExcelTs(codes);
        long t1 = System.currentTimeMillis();
        ExcelUtils.writeExcel(response, resultList, ExeclResponeTs.class);
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
    public BasePageResponse<QueryPromotionListRespone> queryPromotionList(HttpServletRequest httpRequest,int pageSize, int pageIndex, String activityCode, String begainTime, String endTime,String type) {
        BasePageResponse<QueryPromotionListRespone> response = BasePageResponse.success(BasePageResponse.class);
        try {
            QueryPromotionListRequest request = new QueryPromotionListRequest();
            request.setPageIndex(pageIndex);
            request.setActivityCode(activityCode);
            request.setPromotionType(type);
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

    /**
     * 查询用户权限列表
     */
    @RequestMapping("/permission/list")
    @ResponseBody
    public BasePageResponse<PermissionDTO> permissionList(HttpServletRequest httpRequest,int pageSize, int pageIndex) {
        BasePageResponse<PermissionDTO> response = BasePageResponse.success(BasePageResponse.class);
        try {
//            手机号姓名共用一个字段
            String mobile = httpRequest.getParameter("mobile");
            response= userRoleService.queryByUserMobile(mobile,pageSize,pageIndex);
        } catch (Exception e) {
            e.printStackTrace();
            response = BasePageResponse.failure(BasePageResponse.class);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     *  新增权限
     */
    @RequestMapping("/permission/add")
    @ResponseBody
    public BaseEntityResponse<Boolean> addPermission(@RequestBody PermissionDTO permissionDTO) {
        BaseEntityResponse<Boolean> response = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            String code="";
            if(CollectionUtils.isNotEmpty(permissionDTO.getRoleCodes())){
                code=String.join(",",permissionDTO.getRoleCodes());
            }
            Boolean result = userRoleService.add(permissionDTO.getName(), permissionDTO.getMobile(), code,permissionDTO.getRoledesc());
            response.setData(BooleanUtils.isTrue(result));
        } catch (Exception e) {
            e.printStackTrace();
            response = BaseEntityResponse.failure(BaseEntityResponse.class);
            response.setMessage(e.getMessage());
        }
        return response;

    }

    /**
     *  修改权限
     */
    @RequestMapping("/permission/edit")
    @ResponseBody
    public BaseEntityResponse<Integer> editPermission(@RequestBody PermissionDTO permissionDTO) {
        BaseEntityResponse<Integer> response = BaseEntityResponse.success(BaseEntityResponse.class);
        Boolean result = false;
        try {
            String code="";
            if(CollectionUtils.isNotEmpty(permissionDTO.getRoleCodes())){
                code=String.join(",",permissionDTO.getRoleCodes());
            }
            if(permissionDTO.getId()!=null){
                result = userRoleService.update(permissionDTO.getId(), code,permissionDTO.getRoledesc());
            }
            if (permissionDTO.getId() == null) {
               result = userRoleService.add(permissionDTO.getName(), permissionDTO.getMobile(), code,permissionDTO.getRoledesc());
            }
            UserRoleDo userRoleDo1 = userRoleService.queryByMobile(permissionDTO.getMobile());
            if(Objects.nonNull(userRoleDo1)){
                response.setData(userRoleDo1.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = BaseEntityResponse.failure(BaseEntityResponse.class);
            response.setMessage(e.getMessage());
        }
        return response;

    }

    /**
     *  查询权限
     */
    @RequestMapping("/permission/query")
    @ResponseBody
    public BaseEntityResponse<PermissionDTO> queryPermission(String id) {
        BaseEntityResponse<PermissionDTO> response = BaseEntityResponse.success(BaseEntityResponse.class);
        try {
            PermissionDTO permissionDTO = new PermissionDTO();
            UserRoleDo userRoleDo = userRoleService.queryById(Integer.valueOf(id));
            permissionDTO.setMobile(userRoleDo.getUserMobile());
            permissionDTO.setName(userRoleDo.getUserName());
            permissionDTO.setId(Integer.valueOf(userRoleDo.getId()));
            if(org.apache.commons.lang3.StringUtils.isNoneBlank(userRoleDo.getRoleCode())){
                List<String> stringList = Arrays.asList(userRoleDo.getRoleCode().split(","));
                permissionDTO.setRoleCodes(stringList);
            }
            permissionDTO.setRoledesc(userRoleDo.getRoleDesc());
            response.setData(permissionDTO);
        } catch (Exception e) {
            e.printStackTrace();
            response = BaseEntityResponse.failure(BaseEntityResponse.class);
            response.setMessage(e.getMessage());
        }
        return response;

    }

    public static void main(String[] args) {
        String s = "CN022003,CN022006,CN022007,CN022011,CN022014,CN022016,CN022017,CN022020,CN022021,CN022023,CN022024,CN022025,CN022026,CN022027,CN022029,CN022030,CN022031,CN022032,CN022034,CN022035,CN022036,CN022037,CN022038,CN022039,CN022040,CN022041,CN022042,CN022043,CN022044,CN022045,CN022046,CN022047,CN022048,CN022049,CN022050,CN022051,CN022052,CN022053,CN022054,CN024004,CN310001,CN310003,CN310005,CN311009,CN311010,CN311011,CN311012,CN311014,CN311015,CN311016,CN311017,CN311018,CN311019,CN311021,CN312001,CN315002,CN315005,CN315006,CN316001,CN319001,CN319002,CN319003,CN319004,CN335001,CN335005,CN335006,CN335007,CN335008,CN371009,CN411001,CN029007,CN029010,CN029011,CN029012,CN029015,CN029016,CN029017,CN029019,CN029020,CN029021,CN029022,CN029023,CN029024,CN029025,CN351004,CN351007,CN351008,CN351009,CN351010,CN351011,CN351012,CN351013,CN351014,CN351015,CN351016,CN354001,CN021001,CN021003,CN021006,CN021008,CN021009,CN021011,CN021012,CN021013,CN021015,CN021016,CN021017,CN021021,CN021022,CN021023,CN021024,CN021025,CN021026,CN021027,CN021028,CN021029,CN021030,CN021032,CN021033,CN021034,CN021036,CN021037,CN021040,CN025001,CN025002,CN025003,CN025006,CN025007,CN025008,CN025009,CN025010,CN025011,CN025012,CN025013,CN025014,CN025016,CN025017,CN025018,CN574002,CN531007,CN531012,CN531013,CN531014,CN531015,CN531016,CN531017,CN531018,CN531019,CN532001,CN532003,CN532006,CN532007,CN532009,CN532010,CN532011,CN532012,CN532013,CN532014,CN532015,CN532016,CN532017,CN532018,CN533002,CN533004,CN533005,CN533006,CN535001,CN535002,CN535003,CN536001,CN536003,CN536004,CN538001,CN027001,CN027002,CN027003,CN027004,CN027006,CN027007,CN027008,CN027009,CN027010,CN027011,CN027012,CN027013,CN027014,CN027015,CN027016,CN027017,CN027018,CN027019,CN027020,CN027021,CN027022,CN312002,CN312003,CN315008,CN317001,CN318001,CN021031,CN021035,CN021038,KDH535001,KDH536001,YF021001,CN022056";
        String[] str = s.split(",");
        List<String> list = Arrays.asList(str);
        String s1 = "CN312002,CN318001,CN317001,CN315008,CN312003";
        String[] str1 = s1.split(",");
        for(int i=0;i<str1.length;i++){
            boolean result = list.contains(str1[i]);
            System.out.println("是否包含"+str1[i]+": "+result);
        }

    }
}
