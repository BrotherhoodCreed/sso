package com.promotion.product.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.promotion.product.common.HttpHelper;
import com.promotion.product.entity.UserDao;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Service
@Slf4j
public class UserAuthsService {


//    @Value("${dingtail.appid:dingoalnu7hn7fqu1gh8cf}")
    private String appid="dingoalnu7hn7fqu1gh8cf";

//    @Value("${dingtail.appSecret:MKpmawqKmqFhGCrH6F7wZ1pt5vf6wpumvcZiGTskrtWXv4NhnmLIPO8ZEoQPrSq4}")
    private String appSecret="MKpmawqKmqFhGCrH6F7wZ1pt5vf6wpumvcZiGTskrtWXv4NhnmLIPO8ZEoQPrSq4";

//    @Value("${dingtail.appkey:dingv6ulx4cvf5ukbn9v}")
    private String appkey = "dingv6ulx4cvf5ukbn9v";

//    @Value("${dingtail.appQYSecret:JbqsU8wJ_xwyetQvMl9soBR8sJal3zpiGiGbnjj6lqcycqe35sxdmqkJrtzCgCAT}")
    private String appQYSecret = "JbqsU8wJ_xwyetQvMl9soBR8sJal3zpiGiGbnjj6lqcycqe35sxdmqkJrtzCgCAT";

    @Value("corpId:ding2fde065d59156498")
    private String corpId;

    /**
     * 通过扫描二维码返回的code值，得到用户相关信息
     */
    public UserDao getDingLogin(String code) throws ApiException {
        UserDao userDao =new UserDao();
        try {
            //获取accesstoken,返回accessToken
            OapiSnsGetuserinfoBycodeResponse userInfo  =getAccesstoken(code);
            //获取企业授权token
            String accessToken = getAccesstoken();
            //查询用户基本信息
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/user/getbyunionid");
            OapiUserGetbyunionidRequest req = new OapiUserGetbyunionidRequest();
            req.setUnionid(userInfo.getUserInfo().getUnionid());
            OapiUserGetbyunionidResponse rsp = client.execute(req, accessToken);
            log.info("获取企业用户useid[{}]",JSON.toJSONString(rsp));

            //查询企业用户信息
            DingTalkClient client2 = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/get");
            OapiV2UserGetRequest req2 = new OapiV2UserGetRequest();
            req2.setUserid(rsp.getResult().getUserid());
            req2.setLanguage("zh_CN");
            OapiV2UserGetResponse rsp2 = client2.execute(req2, accessToken);
            log.info("获取企业用户信息[{}]",JSON.toJSONString(rsp2));
            if(rsp2.isSuccess()){
               BeanUtils.copyProperties(rsp2.getResult(),userDao);
                return userDao;
            }
        } catch (Exception e) {
           throw  e;
        }
        return userDao;
    }

    public OapiSnsGetuserinfoBycodeResponse getAccesstoken(String authCode) throws ApiException{
        DefaultDingTalkClient  client = new DefaultDingTalkClient("https://oapi.dingtalk.com/sns/getuserinfo_bycode");
// 如果您使用的SDK中不存在该类，请下载最新的SDK
        OapiSnsGetuserinfoBycodeRequest req = new OapiSnsGetuserinfoBycodeRequest();
        req.setTmpAuthCode(authCode);
        OapiSnsGetuserinfoBycodeResponse response = client.execute(req,appid,appSecret);
        log.info("获取用户信息[{}]", JSON.toJSONString(response));
        return response;

    }

    /**
     * 获取accesstoken
     */
    public String getAccesstoken() throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(appkey);
        request.setAppsecret(appQYSecret);
        request.setHttpMethod("GET");
        OapiGettokenResponse response = client.execute(request);
        return response.getAccessToken();

    }



    /**
     * 获取用户昵称和snsToken
     */
    public JSONObject getUserName(String snsToken) {
        String url = "https://oapi.dingtalk.com/sns/getuserinfo?sns_token="
                +snsToken;
        JSONObject json = HttpHelper.httpGet(url);
        if(null!=json){
            return json;
        }
        return null;
    }




}
