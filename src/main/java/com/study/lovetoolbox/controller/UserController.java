package com.study.lovetoolbox.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.study.lovetoolbox.annotation.AuthCheck;
import com.study.lovetoolbox.common.BaseResponse;
import com.study.lovetoolbox.common.DeleteRequest;
import com.study.lovetoolbox.common.ErrorCode;
import com.study.lovetoolbox.common.ResultUtils;
import com.study.lovetoolbox.constant.UserConstant;
import com.study.lovetoolbox.exception.BusinessException;
import com.study.lovetoolbox.exception.ThrowUtils;
import com.study.lovetoolbox.model.dto.user.*;
import com.study.lovetoolbox.model.entity.User;
import com.study.lovetoolbox.model.vo.UserVO;
import com.study.lovetoolbox.service.UserService;
import com.study.lovetoolbox.service.impl.UserServiceImpl;
import com.study.lovetoolbox.utils.AuthUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户接口
 *
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户手机号注册
     *
     * @param dto
     * @return
     */
    // todo
    @PostMapping("/register-phone")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "用户手机号注册", notes = "userRegisterRequest")
    public BaseResponse<Long> userRegisterByPhone(@RequestBody UserRegisterDTO dto) {
        if (ObjectUtils.isEmpty(dto)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.success(null);
    }

    /**
     * 用户登录
     *
     * @param userLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "用户登录", notes = "userRegisterRequest")
    public BaseResponse<UserVO> userLogin(@RequestBody UserLoginDTO userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserVO loginUserVO = userService.userLogin(userAccount, userPassword, request, "1");
        return ResultUtils.success(loginUserVO);
    }

    /**
     * 申请绑定
     *
     * @param account
     * @return
     */
    @PostMapping("/apply-binding-relationship")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "申请绑定", notes = "对方account")
    public BaseResponse<Boolean> applyBindingRelationship(String account) {
        return ResultUtils.success(userService.applyBindingRelationship(account));
    }

    /**
     * 绑定关系
     *
     * @param id
     * @return
     */
    @PostMapping("/binding-relationship")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "申请绑定", notes = "消息id")
    public BaseResponse<Boolean> bindingRelationship(Long id) {
        return ResultUtils.success(userService.bindingRelationship(id));
    }

    /**
     * 解绑
     *
     * @return
     */
    @PostMapping("/unbind-relationship")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "解绑", notes = "")
    public BaseResponse<Boolean> unbindRelationship() {
        return ResultUtils.success(userService.unbindRelationship());
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "退出登录", notes = "")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    /**
     * 获取当前登录用户
     *
     * @return
     */
    @GetMapping("/get/login")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "获取当前登录用户", notes = "")
    public BaseResponse<UserVO> getLoginUser() {
        return ResultUtils.success(AuthUtils.getCurrentUser());
    }

    /**
     * 创建用户
     *
     * @param userAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addUser(@RequestBody UserAddDTO userAddRequest, HttpServletRequest request) {
        if (userAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(userAddRequest, user);
        // 默认密码 12345678
        String defaultPassword = "12345678";
        String encryptPassword = DigestUtils.md5DigestAsHex((UserServiceImpl.SALT + defaultPassword).getBytes());
        user.setUserPassword(encryptPassword);
        boolean result = userService.save(user);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(user.getId());
    }

    /**
     * 用户注销-删除账号
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = userService.removeById(deleteRequest.getId());
        return ResultUtils.success(b);
    }

    /**
     * 更新用户
     *
     * @param userUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateUser(@RequestBody UserUpdateDTO userUpdateRequest,
            HttpServletRequest request) {
        if (userUpdateRequest == null || userUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(userUpdateRequest, user);
        boolean result = userService.updateById(user);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    /**
     * 根据 id 获取用户（仅管理员）
     *
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<User> getUserById(long id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getById(id);
        ThrowUtils.throwIf(user == null, ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.success(user);
    }

    /**
     * 分页获取用户列表（仅管理员）
     *
     * @param userQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<User>> listUserByPage(@RequestBody UserQueryDTO userQueryRequest,
            HttpServletRequest request) {
        long current = userQueryRequest.getCurrent();
        long size = userQueryRequest.getPageSize();
        Page<User> userPage = userService.page(new Page<>(current, size),
                userService.getQueryWrapper(userQueryRequest));
        return ResultUtils.success(userPage);
    }


    // endregion

    /**
     * 更新个人信息
     *
     * @param userUpdateMyRequest
     * @return
     */
    @PostMapping("/update/my")
    public BaseResponse<Boolean> updateMyUser(@RequestBody UserUpdateMyDTO userUpdateMyRequest) {
        if (userUpdateMyRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserVO loginUser = AuthUtils.getCurrentUser();
        User user = new User();
        BeanUtils.copyProperties(userUpdateMyRequest, user);
        user.setId(loginUser.getId());
        boolean result = userService.updateById(user);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }
}
