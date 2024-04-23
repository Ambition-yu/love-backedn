package com.study.lovetoolbox.model.vo;

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
public class RecipeDetailVO implements Serializable {
    /**
     * id
     */
    private Long id;

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

    /**
     * 创建人
     */
    private Long userId;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}