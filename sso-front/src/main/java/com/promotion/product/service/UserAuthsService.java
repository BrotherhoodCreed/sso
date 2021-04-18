package com.promotion.product.service;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.promotion.product.common.HttpHelper;
import com.sun.deploy.net.HttpResponse;
import com.sun.java.browser.plugin2.liveconnect.v1.Result;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sun.net.www.http.HttpClient;
import sun.rmi.runtime.Log;

import java.util.Objects;

@Service
@Slf4j
public class UserAuthsService {
    @Value("dingtail.appid")
    private String appid;
 
    @Value("dingtail.appSecret")
    private String appSecret;

    @Value("corpId:ding2fde065d59156498")
    private String corpId;

    /**
     * 通过扫描二维码返回的code值，得到用户相关信息
     */
    public String getDingLogin(String code) {
        String logStr ="";
        try {
            //获取accesstoken,返回accessToken
            OapiSnsGetuserinfoBycodeResponse userInfo  =getAccesstoken(code);
            logStr+="getDingLogin  根据 code 查询用户基本信息{}"+JSONObject.toJSON(userInfo)+"\n\t";
            String accessToken = getAccesstoken();
            logStr+="https://oapi.dingtalk.com/sns/gettoken 查询用户token{}"+accessToken+"\n\t";
            //获取用户授权的持久授权码，返回accessToken
            OapiSnsGetPersistentCodeResponse oapiSnsGetPersistentCodeResponse = getPersistentCode(accessToken, code);
            log.info("获取用户授权的持久授权码{}",JSONObject.toJSON(oapiSnsGetPersistentCodeResponse));
            logStr+="获取用户授权的持久授权码"+JSONObject.toJSON(oapiSnsGetPersistentCodeResponse)+"\n\t";
            String openId = "";
            String persistentCode = "";
            if (Objects.isNull(oapiSnsGetPersistentCodeResponse)) {
                openId =oapiSnsGetPersistentCodeResponse.getOpenid();
                persistentCode =oapiSnsGetPersistentCodeResponse.getPersistentCode();
            }
            //获取用户授权的SNS_TOKEN，返回snsToken
            String snsToken = getSnsToken(accessToken, openId, persistentCode);
            logStr+="获取用户SNS——token:"+snsToken+"\n\t";
            //获取用户的昵称和dingId
            JSONObject userJson = getUserName(snsToken);
            logStr+="获取用户的昵称和dingId :"+userJson+"\n\t";
            if (Integer.parseInt(userJson.get("errcode").toString()) == 0) {
                JSONObject jsonUser = userJson.getJSONObject("user_info");
                String nick = jsonUser.getString("nick");
                String dingId = jsonUser.getString("dingId");
                String openid = jsonUser.getString("openid");
                String errmsg = jsonUser.getString("errmsg");
                String unionid = jsonUser.getString("unionid");
            }

//            //获取用户unionid
//            String unionId = getUnionId(snsToken);
//
//            //根据unionid获取用户userId,需要企业ID和企业秘钥
//            String appAccessToken = getAppAccesstoken();
//            String userId = getUserId(appAccessToken, unionId);
//            if (StringUtils.isEmpty(unionId)) {
//                return null;
//            }
//
//            //获取用户详细数据
//            JSONObject userData = getUserData(appAccessToken, userId);
        } catch (Exception e) {
            logStr+=e.getMessage();
        }
        return logStr;
    }

    public OapiSnsGetuserinfoBycodeResponse getAccesstoken(String authCode) throws ApiException{
        DefaultDingTalkClient  client = new DefaultDingTalkClient("https://oapi.dingtalk.com/sns/getuserinfo_bycode");
// 如果您使用的SDK中不存在该类，请下载最新的SDK
        OapiSnsGetuserinfoBycodeRequest req = new OapiSnsGetuserinfoBycodeRequest();
        req.setTmpAuthCode(authCode);
        OapiSnsGetuserinfoBycodeResponse response = client.execute(req,appid,appSecret);
        return response;

    }

    /**
     * 获取accesstoken
     */
    public String getAccesstoken() throws ApiException {

        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/sns/gettoken");
        OapiSnsGettokenRequest request = new OapiSnsGettokenRequest();
        request.setAppid(appid);
        request.setAppsecret(appSecret);
        OapiSnsGettokenResponse oapiSnsGettokenResponse = client.execute(request);
        return oapiSnsGettokenResponse.getAccessToken();

    }

    /**
     * 获取用户授权的持久授权码
     */
    public OapiSnsGetPersistentCodeResponse getPersistentCode(String access_token,String code) throws ApiException {
        DefaultDingTalkClient client =new DefaultDingTalkClient("https://oapi.dingtalk.com/sns/get_persistent_code");
        OapiSnsGetPersistentCodeRequest req = new OapiSnsGetPersistentCodeRequest();
        req.setTmpAuthCode(code);
        OapiSnsGetPersistentCodeResponse rsp = client.execute(req, access_token);
        return rsp;
//        String url = "https://oapi.dingtalk.com/sns/get_persistent_code?access_token="
//                + accessToken;
//
//        JSONObject jsonData = new JSONObject();
//        jsonData.put("tmp_auth_code", code);
//        JSONObject json = ossHttpPostUtil(url, jsonData);
//        if (null != json) {
//            if (Integer.parseInt(json.get("errcode").toString()) == 0) {
//                return json;
//            }
//        }
    }

    /**
     * 获取用户授权的SNS_TOKEN
     */
    public String getSnsToken(String accesstoken, String openid, String persistent_code) throws ApiException {
        DefaultDingTalkClient client =new DefaultDingTalkClient("https://oapi.dingtalk.com/sns/get_sns_token");
        OapiSnsGetSnsTokenRequest req = new OapiSnsGetSnsTokenRequest();
        req.setOpenid(openid);
        req.setPersistentCode(persistent_code);
        OapiSnsGetSnsTokenResponse oapiSnsGetSnsTokenResponse =client.execute(req,accesstoken);
        return  oapiSnsGetSnsTokenResponse.getSnsToken();
//        String url = "https://oapi.dingtalk.com/sns/get_sns_token?access_token="
//                + accesstoken;
//
//        HttpPost httpPost = new HttpPost(url);
//        JSONObject jsonData = new JSONObject();
//        jsonData.put("openid", openid);
//        jsonData.put("persistent_code", persistent_code);
//        JSONObject json = ossHttpPostUtil(url, jsonData);
//
//        if (null != json) {
//            if (Integer.parseInt(json.get("errcode").toString()) == 0) {
//                String snsToken = json.getString("sns_token");
//                return snsToken;
//            }
//        }
//        return null;
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

    /**
     * 获取用户unionid
     */
    public String getUnionId(String snsToken){
        String url = "https://oapi.dingtalk.com/sns/getuserinfo?sns_token="
                + snsToken;
        JSONObject json = HttpHelper.httpGet(url);
        if (null != json) {
            if (Integer.parseInt(json.get("errcode").toString()) == 0) {
                JSONObject jsonUser = json.getJSONObject("user_info");
                String unionid = jsonUser.getString("unionid");
                return unionid;
            }
        }
        return "";
    }

//    /**
//     * 获取appAccesstoken信息(corpid（企业ID）和corpsecret（企业密钥）)
//     */
//    public String getAppAccesstoken()  {
//        String url = "https://oapi.dingtalk.com/gettoken?corpid="
//                + DingTalkConstant.DING_TALK_CORP_ID
//                + "&corpsecret="
//                + DingTalkConstant.DING_TALK_CORP_SECRET;
//        JSONObject json = HttpHelper.httpGet(url);
//        if (null != json) {
//            if (Integer.parseInt(json.get("errcode").toString()) == 0) {
//                String appAccessToken = json.getString("access_token");
//                return appAccessToken;
//            }
//        }
//        return "";
//    }

    /**
     * 获取用户详细数据
     */
    public JSONObject getUserData(String accessToken, String userId)  {
        String url = "https://oapi.dingtalk.com/user/get?access_token=" + accessToken + "&userid=" + userId;
        JSONObject json = HttpHelper.httpGet(url);
        if (null != json) {
            if (Integer.parseInt(json.get("errcode").toString()) == 0) {
                return json;
            }
        }
        return null;
    }

    /**
     * 根据unionid获取用户userId
     */
    public String getUserId(String accessToken, String unionId)  {
        String url = "https://oapi.dingtalk.com/user/getUseridByUnionid?unionid=" + unionId + "&access_token=" + accessToken;
        JSONObject json = HttpHelper.httpGet(url);
        if (null != json) {
            if (Integer.parseInt(json.get("errcode").toString()) == 0) {
                String userId = json.getString("userid");
                return userId;
            }
        }
        return "";
    }



}
