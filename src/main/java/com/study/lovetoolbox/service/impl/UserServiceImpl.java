package com.study.lovetoolbox.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.lovetoolbox.common.ErrorCode;
import com.study.lovetoolbox.constant.CommonConstant;
import com.study.lovetoolbox.exception.BusinessException;
import com.study.lovetoolbox.exception.ThrowUtils;
import com.study.lovetoolbox.mapper.UserMapper;
import com.study.lovetoolbox.model.dto.user.UserQueryDTO;
import com.study.lovetoolbox.model.dto.user.UserRegisterDTO;
import com.study.lovetoolbox.model.entity.User;
import com.study.lovetoolbox.model.entity.UserNotice;
import com.study.lovetoolbox.model.enums.UserRoleEnum;
import com.study.lovetoolbox.model.vo.UserVO;
import com.study.lovetoolbox.service.UserNoticeService;
import com.study.lovetoolbox.service.UserService;
import com.study.lovetoolbox.utils.AuthUtils;
import com.study.lovetoolbox.utils.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.study.lovetoolbox.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户服务实现
 *
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserNoticeService userNoticeService;

    @Resource
    private RedissonClient redissonClient;

    /**
     * 盐值，混淆密码
     */
    public static final String SALT = "lover";

    @Override
    public UserVO userLogin(String userAccount, String userPassword, HttpServletRequest request, String type) {
        // 1. 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
//       分布式锁
//        RLock lock = redissonClient.getLock("lovetool:userlogin:{userAccount}");
//        try {
//            if (lock.tryLock(0, -1, TimeUnit.MILLISECONDS)) {
//                // ...
//            }
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            // 释放锁
//            if (lock.isHeldByCurrentThread()) {
//                lock.unlock();
//            }
//        }
        synchronized (userAccount.intern()) {
            // 2. 加密
            String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
            // 查询用户是否存在
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_account", userAccount);
            User user = getOne(queryWrapper);
            // todo 手机号注册/account 绑定手机号
            // 用户不存在 - 注册新用户
            if (ObjectUtils.isEmpty(user)) {
                UserRegisterDTO register = new UserRegisterDTO();
                register.setUserAccount(userAccount);
                register.setUserPassword(userPassword);
                user = userRegister(register);
            }
            if (!user.getUserPassword().equals(encryptPassword)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
            }
            // 3. 记录用户的登录态
            request.getSession().setAttribute(USER_LOGIN_STATE, user);
            UserVO result = new UserVO();
            BeanUtils.copyProperties(user, result);
            return result;
        }
    }

    @Override
    public boolean applyBindingRelationship(String account) {
        User user = getById(AuthUtils.getCurrentUser().getId());
        ThrowUtils.throwIf(ObjectUtils.isNotEmpty(user.getRelationId()), ErrorCode.OPERATION_ERROR, "已有关系绑定人");
        // 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", account);
        User userRelation = getOne(queryWrapper);
        ThrowUtils.throwIf(ObjectUtils.isEmpty(userRelation), ErrorCode.NOT_FOUND_ERROR, "该用户不存在");
        UserNotice notice = new UserNotice();
        notice.setCreateUser(user.getId());
        notice.setUserId(userRelation.getId());
        notice.setContent("【申请消息】用户【"+userRelation.getUserName()+"】申请与您绑定关系");
        return userNoticeService.save(notice);
    }

    @Override
    public boolean bindingRelationship(Long id) {
        UserNotice notice = userNoticeService.getById(id);
        List<User> list = new ArrayList<>();
        User userA = getById(notice.getUserId());
        User userB = getById(notice.getCreateUser());
        list.add(userA);
        list.add(userB);
        if (ObjectUtils.isNotEmpty(userA) && ObjectUtils.isNotEmpty(userB)) {
            userA.setRelationId(notice.getCreateUser());
            userB.setRelationId(notice.getUserId());
            return updateBatchById(list);
        }
        return false;
    }

    @Override
    public boolean unbindRelationship() {
        UserVO currentUser = AuthUtils.getCurrentUser();
        if (ObjectUtils.isNotEmpty(currentUser.getRelationId())) {
            List<Long> ids = new ArrayList<>();
            ids.add(currentUser.getId());
            ids.add(currentUser.getRelationId());
            return baseMapper.unbindRelation(ids);
        }
        return false;
    }

    @Override
    public boolean isAdmin(User user) {
        return user != null && UserRoleEnum.ADMIN.getValue().equals(user.getUserRole());
    }

    /**
     * 退出登录
     *
     * @param request
     */
    @Override
    public boolean userLogout(HttpServletRequest request) {
        if (request.getSession().getAttribute(USER_LOGIN_STATE) == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "未登录");
        }
        // 移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
    }

    @Override
    public UserVO getUserVO(User user) {
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public List<UserVO> getUserVO(List<User> userList) {
        if (CollUtil.isEmpty(userList)) {
            return new ArrayList<>();
        }
        return userList.stream().map(this::getUserVO).collect(Collectors.toList());
    }

    @Override
    public QueryWrapper<User> getQueryWrapper(UserQueryDTO userQueryRequest) {
        if (userQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = userQueryRequest.getId();
        String unionId = userQueryRequest.getUnionId();
        String mpOpenId = userQueryRequest.getMpOpenId();
        String userName = userQueryRequest.getUserName();
        String userProfile = userQueryRequest.getUserProfile();
        String userRole = userQueryRequest.getUserRole();
        String sortField = userQueryRequest.getSortField();
        String sortOrder = userQueryRequest.getSortOrder();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(StringUtils.isNotBlank(unionId), "union_id", unionId);
        queryWrapper.eq(StringUtils.isNotBlank(mpOpenId), "mp_open_id", mpOpenId);
        queryWrapper.eq(StringUtils.isNotBlank(userRole), "user_role", userRole);
        queryWrapper.like(StringUtils.isNotBlank(userProfile), "user_profile", userProfile);
        queryWrapper.like(StringUtils.isNotBlank(userName), "user_name", userName);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    private User userRegister(UserRegisterDTO dto) {
        String userAccount = dto.getUserAccount();
        String userPassword = dto.getUserPassword();
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号过短");
        }
        if (userPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码过短");
        }
        synchronized (userAccount.intern()) {
            // 2. 加密
            String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
            // 3. 插入数据
            User user = new User();
            user.setUserAccount(userAccount);
            user.setUserName(userAccount);
            user.setUserPassword(encryptPassword);
            boolean saveResult = this.save(user);
            if (!saveResult) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
            }
            return user;
        }
    }
}
