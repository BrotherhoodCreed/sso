package com.promotion.product.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.promotion.product.common.HttpHelper;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class UserAuthsService {
    private String appid="dingoalnu7hn7fqu1gh8cf";
 
    private String appSecret="MKpmawqKmqFhGCrH6F7wZ1pt5vf6wpumvcZiGTskrtWXv4NhnmLIPO8ZEoQPrSq4";

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
            String accessToken = getAccesstoken();
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/user/getbyunionid");
            OapiUserGetbyunionidRequest req = new OapiUserGetbyunionidRequest();
            req.setUnionid(userInfo.getUserInfo().getUnionid());
            OapiUserGetbyunionidResponse rsp = client.execute(req, accessToken);
            log.info("获取企业用户useid[{}]",JSON.toJSONString(rsp));

            DingTalkClient client2 = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/get");
            OapiV2UserGetRequest req2 = new OapiV2UserGetRequest();
            req2.setUserid(rsp.getResult().getUserid());
            req2.setLanguage("zh_CN");
            OapiV2UserGetResponse rsp2 = client2.execute(req2, accessToken);
            log.info("获取企业用户信息[{}]",JSON.toJSONString(rsp2));

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
        log.info("获取用户信息[{}]", JSON.toJSONString(response));
        return response;

    }

    /**
     * 获取accesstoken
     */
    public String getAccesstoken() throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey("dingv6ulx4cvf5ukbn9v");
        request.setAppsecret("JbqsU8wJ_xwyetQvMl9soBR8sJal3zpiGiGbnjj6lqcycqe35sxdmqkJrtzCgCAT");
        request.setHttpMethod("GET");
        OapiGettokenResponse response = client.execute(request);
        return response.getAccessToken();

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
