package com.study.lovetoolbox.model.dto.recipe;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 食谱明细表
 * @TableName k_recipe_detail
 */
@Data
public class RecipeDetailDTO implements Serializable {


    /**
     * 食谱id
     */
    private Long recipeId;

    /**
     * 食物名
     */
    private String foodName;

    /**
     * 食物图片
     */
    private String foodIcon;

    /**
     * 食物分类
     */
    private Long foodCategoryId;

    /**
     * 价格
     */
    private BigDecimal price;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}