package com.study.lovetoolbox.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.study.lovetoolbox.model.dto.user.UserQueryDTO;
import com.study.lovetoolbox.model.entity.User;
import com.study.lovetoolbox.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户服务
 *
 */
public interface UserService extends IService<User> {

    /**
     * 用户登录/注册
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @param type 1 account 2 phone
     * @return 脱敏后的用户信息
     */
    UserVO userLogin(String userAccount, String userPassword, HttpServletRequest request, String type);

    /**
     * 申请绑定关系
     *
     * @param account
     * @return
     */
    boolean applyBindingRelationship(String account);

    /**
     * 绑定关系
     *
     * @param id 消息id
     * @return
     */
    boolean bindingRelationship(Long id);

    /**
     * 解绑
     *
     * @return
     */
    boolean unbindRelationship();

    /**
     * 是否为管理员
     *
     * @param user
     * @return
     */
    boolean isAdmin(User user);

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获取脱敏的用户信息
     *
     * @param user
     * @return
     */
    UserVO getUserVO(User user);

    /**
     * 获取脱敏的用户信息
     *
     * @param userList
     * @return
     */
    List<UserVO> getUserVO(List<User> userList);

    /**
     * 获取查询条件
     *
     * @param userQueryRequest
     * @return
     */
    QueryWrapper<User> getQueryWrapper(UserQueryDTO userQueryRequest);

}
