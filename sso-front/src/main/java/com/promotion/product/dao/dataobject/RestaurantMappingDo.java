package com.promotion.product.dao.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class RestaurantMappingDo {

    private Long id;

    private Integer promotionBaseInfoId;

    private String area;

    private String city;

    private String restaurantCode;

    private Date created_time;

    private String created_user;

    private Boolean deleted;

    private Date updated_time;

    private Date updated_user;

}
