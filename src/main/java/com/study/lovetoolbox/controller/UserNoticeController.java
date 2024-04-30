package com.study.lovetoolbox.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.study.lovetoolbox.common.BaseResponse;
import com.study.lovetoolbox.common.ResultUtils;
import com.study.lovetoolbox.model.dto.common.Query;
import com.study.lovetoolbox.model.dto.notice.NoticePageDTO;
import com.study.lovetoolbox.model.entity.UserNotice;
import com.study.lovetoolbox.service.UserNoticeService;
import com.study.lovetoolbox.utils.PageUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 消息通知接口
 *
 */
@RestController
@RequestMapping("/user-notice")
@Slf4j
public class UserNoticeController {

    @Resource
    private UserNoticeService userNoticeService;

    /**
     *  列表
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "列表", notes = "")
    public BaseResponse<List<UserNotice>> list(Integer status) {
        return ResultUtils.success(userNoticeService.getUserNoticeList(status));
    }

    /**
     *  列表-分页
     */
    @GetMapping("/page")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "列表", notes = "")
    public BaseResponse<IPage<UserNotice>> page(NoticePageDTO dto, Query query) {
        return ResultUtils.success(userNoticeService.getUserNoticePage(PageUtil.getPage(query), dto));
    }


}
