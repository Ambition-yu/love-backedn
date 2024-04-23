package com.study.lovetoolbox.model.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MenuDetailListVO {

    /**
     * 食物id
     */
    private Long recipeDetailId;

    /**
     * 食物名
     */
    private String foodName;

    /**
     * 食物图片
     */
    private String foodIcon;

    /**
     * 价格
     */
    private BigDecimal price;
}
