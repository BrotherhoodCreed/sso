package com.promotion.product.controller;

import com.promotion.product.service.UserAuthsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
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
    public String detail(){
        return "detail";
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

    @RequestMapping("/tree")
    public String tree(){
        return "tree";
    }

    @RequestMapping("/loginTest")
    public String dingDingLogin(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String time = String.valueOf(System.currentTimeMillis());
        StringBuilder stringBuilder = new StringBuilder();
        String result="";
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
        response.sendRedirect( stringBuilder.toString());
        try {
            result = stringBuilder.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     *  钉钉回调验证
     */
    @RequestMapping(value="/login", produces="text/html; charset=utf-8")
    public String getUserInfo(HttpServletRequest request, HttpServletResponse response, Model model, String code, String state) {
        Map<String,String[]> map = request.getParameterMap();
        Iterator<Map.Entry<String,String[]>> iterator =  map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String[]> entry = iterator.next();
            log.info("钉钉回调验证参数key[{}]value[{}]",entry.getKey(),entry.getValue()[0]);
        }
        String result = userAuthsService.getDingLogin(code);
        return result;
    }




}
