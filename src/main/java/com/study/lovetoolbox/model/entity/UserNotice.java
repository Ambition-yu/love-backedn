package com.study.lovetoolbox.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户消息表
 * @TableName user_notice
 */
@TableName(value ="user_notice")
@Data
public class UserNotice implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 接收人id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 消息内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 状态 0正常 1过期
     */
    @TableField(value = "state")
    private Integer state;
    /**
     * 创建人id
     */
    @TableField(value = "create_user")
    private Long createUser;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 是否删除
     */
    @TableField(value = "is_deleted")
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}