package com.promotion.product.controller;

import com.promotion.product.entity.BaseEntityResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("PromotionController")
public class PromotionController {


    @PostMapping("a")
    public BaseEntityResponse<Boolean> a() {
        BaseEntityResponse<Boolean> baseEntityResponse =BaseEntityResponse.success(BaseEntityResponse.class);
        return baseEntityResponse;
    }

}
