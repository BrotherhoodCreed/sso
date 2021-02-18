package com.promotion.product.dao.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class queryPromotionListRespone {

    /**
     *促销编码
     */
    private String activityCode;
    /**
     *活动类型
     */
    private String activityType;
    /**
     *开始时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date salesStartTime;
    /**
     *结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date salesEndTime;




}
