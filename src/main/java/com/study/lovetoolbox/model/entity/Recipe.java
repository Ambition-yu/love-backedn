package com.study.lovetoolbox.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 食谱表
 * @TableName k_recipe
 */
@TableName(value ="k_recipe")
@Data
public class Recipe implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 创建人
     */
    private Long userId;

    /**
     * 食谱名
     */
    private String recipeName;

    /**
     * 是否启用价格
     */
    private Integer isEnablePrice;

    /**
     * 是否启用图片
     */
    private Integer isEnablePicture;

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