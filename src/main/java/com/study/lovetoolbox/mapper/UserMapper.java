package com.study.lovetoolbox.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.lovetoolbox.model.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户数据库操作
 *
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 解绑关系
     *
     * @return
     */
    boolean unbindRelation(@Param("ids") List<Long> ids);

}




