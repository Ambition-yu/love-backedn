package com.study.lovetoolbox.utils;

import com.study.lovetoolbox.common.ErrorCode;
import com.study.lovetoolbox.exception.BusinessException;
import com.study.lovetoolbox.model.entity.User;
import com.study.lovetoolbox.model.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.study.lovetoolbox.constant.UserConstant.USER_LOGIN_STATE;

@Component
@Slf4j
public class AuthUtils {

    public static UserVO getCurrentUser() {

        HttpServletRequest request = getRequest();

        if (ObjectUtils.isNotEmpty(request)) {
            // 先判断是否已登录
            Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
            UserVO result = new UserVO();
            BeanUtils.copyProperties(userObj, result);
            if (ObjectUtils.isEmpty(result) || ObjectUtils.isEmpty(result.getId())) {
                throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
            };

            return result;
        }
        throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);

    }

    private static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : ((ServletRequestAttributes)requestAttributes).getRequest();
    }
}
