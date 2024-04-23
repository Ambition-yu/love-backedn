package com.study.lovetoolbox.model.dto.recipe;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 食谱表
 */
@Data
public class RecipeDTO implements Serializable {

    /**
     * 食谱名
     */
    @ApiModelProperty(value = "食谱名")
    private String recipeName;

    /**
     * 是否启用价格
     */
    @ApiModelProperty(value = "是否启用价格")
    private Integer isEnablePrice;

    /**
     * 是否启用图片
     */
    @ApiModelProperty(value = "是否启用图片")
    private Integer isEnablePicture;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}