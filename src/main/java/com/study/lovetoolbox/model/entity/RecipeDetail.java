package com.study.lovetoolbox.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 食谱明细表
 * @TableName k_recipe_detail
 */
@TableName(value ="k_recipe_detail")
@Data
public class RecipeDetail implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
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
    private String foodCategory;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 创建人
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}