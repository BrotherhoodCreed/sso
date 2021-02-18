package com.promotion.product.controller;

import com.alibaba.fastjson.JSONObject;
import com.promotion.product.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {



    @Autowired
    private TestDao testDao;


    @RequestMapping("/add")
    public String detail(){
        return "detail";
    }

    @RequestMapping("/edit")
    public ModelAndView edit(String id){
        ModelAndView mv = new ModelAndView("edit");
        mv.addObject("id",id);
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
}
