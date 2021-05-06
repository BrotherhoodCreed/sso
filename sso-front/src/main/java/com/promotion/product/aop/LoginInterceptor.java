package com.promotion.product.aop;

import com.alibaba.fastjson.JSONObject;
import com.promotion.product.config.Constans;
import com.promotion.product.entity.UserDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
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
        if (uri.indexOf("/add") >= 0 || uri.indexOf("oapi.dingtalk.com")>0) {
            return true;
        }
        String a = "{\n" +
                "\t\"active\": true,\n" +
                "\t\"admin\": true,\n" +
                "\t\"avatar\": \"\",\n" +
                "\t\"boss\": false,\n" +
                "\t\"deptIdList\": [3780172],\n" +
                "\t\"deptOrderList\": [{\n" +
                "\t\t\"deptId\": 3780172,\n" +
                "\t\t\"order\": 176299235511477512\n" +
                "\t}],\n" +
                "\t\"exclusiveAccount\": false,\n" +
                "\t\"hideMobile\": true,\n" +
                "\t\"jobNumber\": \"\",\n" +
                "\t\"leaderInDept\": [{\n" +
                "\t\t\"deptId\": 3780172,\n" +
                "\t\t\"leader\": false\n" +
                "\t}],\n" +
                "\t\"mobile\": \"17721141697\",\n" +
                "\t\"name\": \"李论\",\n" +
                "\t\"realAuthed\": false,\n" +
                "\t\"roleList\": [{\n" +
                "\t\t\"groupName\": \"默认\",\n" +
                "\t\t\"id\": 49332111,\n" +
                "\t\t\"name\": \"主管理员\"\n" +
                "\t}],\n" +
                "\t\"senior\": true,\n" +
                "\t\"stateCode\": \"86\",\n" +
                "\t\"title\": \"\",\n" +
                "\t\"unionEmpExt\": {},\n" +
                "\t\"unionid\": \"CEaNaPtvwOfZE3NuiSXWlcgiEiE\",\n" +
                "\t\"userid\": \"1522243761855596\"\n" +
                "}";
        UserDao userDao =JSONObject.parseObject(a,UserDao.class);
        if(StringUtils.isNotEmpty(userDao.getMobile())) {
            request.getSession().setAttribute(Constans.USER_CONTENT, userDao);
        }
//        Cookie[] cookies = request.getCookies();
//        if(cookies!=null && cookies.length>0){
//            Cookie cookieValue =  Arrays.stream(cookies).filter(item-> StringUtils.equals("access_token",item.getName())).findFirst().orElse(null);
//            if(Objects.nonNull(cookieValue) && null != cookieValue.getValue()){
//                String userJson = URLDecoder.decode(cookieValue.getValue(),"UTF-8");
//                UserDao userDao =JSONObject.parseObject(userJson,UserDao.class);
//                if(StringUtils.isNotEmpty(userDao.getMobile())){
//                    request.getSession().setAttribute(Constans.USER_CONTENT,userDao);
//                    return true;
//                }
//            }
//        }
//        String time = String.valueOf(System.currentTimeMillis());
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder
//                .append("https://oapi.dingtalk.com/connect/qrconnect?appid=")
//                .append(appid)
//                .append("&response_type=")
//                .append("code")
//                .append("&scope=")
//                .append("snsapi_login")
//                .append("&state=")
//                .append(time)
//                .append("&redirect_uri=")
//                .append(redirectUrl);
//        //不符合条件的给出提示信息，并转发到登录页面
////        request.setAttribute("msg", "您还没有登录，请先登录！");
////        request.getRequestDispatcher(stringBuilder.toString()).forward(request, response);
//        response.sendRedirect(stringBuilder.toString());
        return true;
    }
}
