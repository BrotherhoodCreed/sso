package com.promotion.product.common;

import java.lang.annotation.*;

/**
 * 自定义实体类所需要的bean(Excel属性标题、位置等)
 *
 * @author Lynch
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {
    /**
     * Excel标题
     * @return
     */
    String value() default "";

    /**
     * Excel从左往右排列位置
     *
     * @return
     */
    int col() default 0;


    /**
     * Excel 单元格类型
     * 0 字符串
     * 1 整数
     * 2 小数
     * 3百分比
     * @return
     */
    int type() default 0;
}