package com.promotion.product.aop;

import com.alibaba.fastjson.JSONObject;
import com.promotion.product.entity.UserDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;


public class LoginInterceptor implements HandlerInterceptor {

    private String appid="dingoalnu7hn7fqu1gh8cf";

    private String redirectUrl="http://center.mrcool.cn:32329/promotion/login";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //获取请求的RUi:去除http:localhost:8080这部分剩下的
        String uri = request.getRequestURI();
        //UTL:除了login.jsp是可以公开访问的，其他的URL都进行拦截控制
        if (uri.indexOf("/login") >= 0 || uri.indexOf("oapi.dingtalk.com")>0) {
            return true;
        }
        Cookie[] cookies = request.getCookies();
        Cookie cookieValue =  Arrays.stream(cookies).filter(item-> StringUtils.equals("access_token",item.getName())).findFirst().orElse(null);
        if(Objects.nonNull(cookieValue)){
            UserDao userDao =JSONObject.parseObject(cookieValue.getValue(),UserDao.class);
            if(StringUtils.isNotEmpty(userDao.getMobile())){
                return true;
            }
        }

        String time = String.valueOf(System.currentTimeMillis());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("https://oapi.dingtalk.com/connect/qrconnect?appid=")
                .append(appid)
                .append("&response_type=")
                .append("code")
                .append("&scope=")
                .append("snsapi_login")
                .append("&state=")
                .append(time)
                .append("&redirect_uri=")
                .append(redirectUrl);
        //不符合条件的给出提示信息，并转发到登录页面
//        request.setAttribute("msg", "您还没有登录，请先登录！");
//        request.getRequestDispatcher(stringBuilder.toString()).forward(request, response);
        response.sendRedirect(stringBuilder.toString());
        return false;
    }
}
