package com.promotion.product.controller;

import com.alibaba.fastjson.JSONObject;
import com.promotion.product.entity.PromotionTypeEnum;
import com.promotion.product.entity.UserDao;
import com.promotion.product.service.UserAuthsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private UserAuthsService userAuthsService;

    @Value("${dingtail.appid}")
    private String appid;

    @Value("${dingtail.appSecret}")
    private String appSecret;

    @Value("${dingtail.redirect.uri}")
    private String redirectUrl;

    @RequestMapping("/add")
    public ModelAndView detail(HttpServletRequest request){
        String viewName=PromotionTypeEnum.getDescByCode(request.getParameter("type"));
        ModelAndView modelAndView = new ModelAndView(viewName);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("year",cal.get(Calendar.YEAR));
        jsonObject.put("month",cal.get(Calendar.MONTH));
        jsonObject.put("date",cal.get(Calendar.DAY_OF_MONTH));
        jsonObject.put("hours",0);
        jsonObject.put("minutes",0);
        jsonObject.put("seconds",0);
        modelAndView.addObject("thisTime",jsonObject);
        return modelAndView;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(String id){
        ModelAndView mv = new ModelAndView("edit");
        mv.addObject("activeCode",id);
        return mv;
    }

    @RequestMapping("/list")
    public String list(){
        return "list";
    }

    @RequestMapping("/hall/list")
    public String hallList(){
        return "hall_list";
    }

    @RequestMapping("/hall/edit")
    public ModelAndView hallEdit(String id){
        ModelAndView mv = new ModelAndView("hall_edit");
        mv.addObject("activeCode",id);
        return mv;
    }

    @RequestMapping("/hall/add")
    public ModelAndView hallDetail(){
        ModelAndView modelAndView = new ModelAndView("hall_detail");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("year",cal.get(Calendar.YEAR));
        jsonObject.put("month",cal.get(Calendar.MONTH));
        jsonObject.put("date",cal.get(Calendar.DAY_OF_MONTH));
        jsonObject.put("hours",0);
        jsonObject.put("minutes",0);
        jsonObject.put("seconds",0);
        modelAndView.addObject("thisTime",jsonObject);
        return modelAndView;
    }

    @RequestMapping("/tree")
    public String tree(){
        return "tree";
    }
//
//    @RequestMapping("/loginTest")
//    public String dingDingLogin(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
//        String time = String.valueOf(System.currentTimeMillis());
//        StringBuilder stringBuilder = new StringBuilder();
//        String result="";
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
//        response.sendRedirect( stringBuilder.toString());
//        try {
//            result = stringBuilder.toString();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return result;
//    }

    /**
     *  钉钉回调验证
     */
    @RequestMapping(value="/login", produces="text/html; charset=utf-8")
    public void getUserInfo(HttpServletRequest request, HttpServletResponse response, Model model, String code, String state) {
        try {
            Map<String,String[]> map = request.getParameterMap();
            Iterator<Map.Entry<String,String[]>> iterator =  map.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String,String[]> entry = iterator.next();
                log.info("钉钉回调验证参数key[{}]value[{}]",entry.getKey(),entry.getValue()[0]);
            }
            UserDao result = userAuthsService.getDingLogin(code);
            if(StringUtils.isNotEmpty(request.getMethod())){
                Cookie cookie =new Cookie("access_token", URLEncoder.encode(JSONObject.toJSONString(result),"UTF-8"));
                cookie.setMaxAge(60*60*24*15);//cookie 15天
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
                response.sendRedirect("/promotion/list");            }
        }catch (Exception e){
            log.error("钉钉回调验证异常，",e);
        }

    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request,HttpServletResponse response){
        try {
            Cookie[] cookies = request.getCookies();
            if (null != cookies) {
                for (Cookie cookie : cookies) {
                    if ("access_token".equals(cookie.getName())){
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        cookie.setValue(null);
                        response.addCookie(cookie);
                        log.info("登出qingchucookie");
                    }
                }
            }
            response.sendRedirect("/promotion/list");
        }catch (Exception e){
            log.error("钉钉回调验证异常，",e);
        }
    }


}
