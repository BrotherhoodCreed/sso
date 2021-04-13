package com.promotion.product.aop;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiServiceGetCorpTokenRequest;
import com.dingtalk.api.request.OapiSnsGetuserinfoBycodeRequest;
import com.dingtalk.api.request.OapiUserGetbyunionidRequest;
import com.dingtalk.api.request.OapiV2UserGetRequest;
import com.dingtalk.api.response.OapiServiceGetCorpTokenResponse;
import com.dingtalk.api.response.OapiSnsGetuserinfoBycodeResponse;
import com.dingtalk.api.response.OapiUserGetbyunionidResponse;
import com.dingtalk.api.response.OapiV2UserGetResponse;
import com.taobao.api.ApiException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class loginAop {

    private final  String appid="dingoalnu7hn7fqu1gh8cf";

    private final String appSecret="MKpmawqKmqFhGCrH6F7wZ1pt5vf6wpumvcZiGTskrtWXv4NhnmLIPO8ZEoQPrSq4";

    @Pointcut("execution(* com.promotion.product.controller..*(..))")
    public void openDingDing(){}

    @Before("openDingDing()")
    public void dobefore(JoinPoint joinPoint){
        HttpServletRequest request = (HttpServletRequest) RequestContextHolder.getRequestAttributes();
        String token=request.getHeader("token");
        Object[] para=joinPoint.getArgs();

        if(StringUtils.isEmpty(token)){
           //服务器重定向
            if(ArrayUtils.isNotEmpty(para)){
                //login()
            }

        }

    }

    /**
     *
     */
    public void login(String code) throws ApiException {
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/sns/getuserinfo_bycode");
        OapiSnsGetuserinfoBycodeRequest req = new OapiSnsGetuserinfoBycodeRequest();
        req.setTmpAuthCode(code);
        OapiSnsGetuserinfoBycodeResponse response = client.execute(req,appid,appSecret);
        if(response.getErrcode()==0){
           String access_token= login_access_token();
           String userId =login_user_id(response.getUserInfo().getUnionid(),access_token);
           login_userInfo(userId,access_token);
        }

    }

    public String  login_access_token() throws ApiException {
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/service/get_corp_token");
        OapiServiceGetCorpTokenRequest req = new OapiServiceGetCorpTokenRequest();
        req.setAuthCorpid("dingc365fcabbf733c3535c2f4657eb6378f");
        OapiServiceGetCorpTokenResponse execute = client.execute(req,"suiteKey","suiteSecrect", "suiteTicket");

        return execute.getAccessToken();

    }

    public String login_user_id(String unionid,String access_token) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/user/getbyunionid");
        OapiUserGetbyunionidRequest req = new OapiUserGetbyunionidRequest();
        req.setUnionid(unionid);
        OapiUserGetbyunionidResponse rsp = client.execute(req, access_token);
        System.out.println(rsp.getBody());
        return rsp.getResult().getUserid();
    }

    public void login_userInfo(String userId,String access_token) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/get");
        OapiV2UserGetRequest req = new OapiV2UserGetRequest();
        req.setUserid(userId);
        req.setLanguage("zh_CN");
        OapiV2UserGetResponse rsp = client.execute(req, access_token);
        System.out.println(rsp.getBody());
    }


}
