package com.study.lovetoolbox.model.dto.menu;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MenuDetailSaveDTO {

    /**
     * menu id
     */
    private Long menuId;

    /**
     * 食物id
     */
    @TableField(value = "recipe_detail_id")
    private Long recipeDetailId;

    /**
     * 食物名
     */
    @TableField(value = "food_name")
    private String foodName;

    /**
     * 食物图片
     */
    @TableField(value = "food_icon")
    private String foodIcon;

    /**
     * 价格
     */
    @TableField(value = "price")
    private BigDecimal price;

}
