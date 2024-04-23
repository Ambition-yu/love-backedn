package com.study.lovetoolbox.model.dto.foodcategory;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜品分类表
 * @TableName k_food_category
 */
@Data
public class FoodCategoryDTO implements Serializable {

    /**
     * 食谱id
     */
    private Long recipeId;

    /**
     * 食物分类名
     */
    private String foodName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}