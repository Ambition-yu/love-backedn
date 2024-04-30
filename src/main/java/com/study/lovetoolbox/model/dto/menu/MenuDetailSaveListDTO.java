package com.study.lovetoolbox.model.dto.menu;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MenuDetailSaveListDTO {

    /**
     * menu id
     */
    private Long menuId;

    /**
     * 食物id
     */
    @ApiModelProperty(value = "recipe_detail_id")
    private Long recipeDetailId;

    /**
     * 食物名
     */
    @ApiModelProperty(value = "food_name")
    private String foodName;

    /**
     * 食物图片
     */
    @ApiModelProperty(value = "food_icon")
    private String foodIcon;

    /**
     * 价格
     */
    @ApiModelProperty(value = "price")
    private BigDecimal price;

}
