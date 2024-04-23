package com.study.lovetoolbox.model.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 食谱表
 * @TableName k_recipe
 */
@Data
public class RecipeVO implements Serializable {

    /**
     * 食谱名
     */
    private Long id;

    /**
     * 食谱名
     */
    private String recipeName;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}