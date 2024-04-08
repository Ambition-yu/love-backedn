package com.study.lovetoolbox.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 菜单表
 * @TableName k_menu
 */
@TableName(value ="k_menu")
@Data
public class Menu implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 食谱id
     */
    private Long recipeId;

    /**
     * 食物分类
     */
    private Long foodCategoryId;

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

    /**
     * 日期
     */
    private Date menuDate;

    /**
     * 餐时
     */
    private Integer mealTime;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private Long updateUser;

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