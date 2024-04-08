package com.study.lovetoolbox.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 用户关系表
 * @TableName user_relation
 */
@TableName(value ="user_relation")
@Data
public class UserRelation implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户a
     */
    @TableField(value = "user_id_a")
    private Long userIdA;

    /**
     * 用户b
     */
    @TableField(value = "user_id_b")
    private Long userIdB;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}