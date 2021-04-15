package com.promotion.product.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Value("dingtail.appid")
    private String appid;

    @Value("dingtail.appSecret")
    private String appSecret;

    @Value("dingtail.redirect.uri")
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

    @RequestMapping("/Login")
    public Object dingDingLogin() {
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
        try {
            result = stringBuilder.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


}
