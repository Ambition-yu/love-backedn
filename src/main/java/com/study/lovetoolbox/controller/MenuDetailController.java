package com.study.lovetoolbox.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.study.lovetoolbox.common.BaseResponse;
import com.study.lovetoolbox.common.ResultUtils;
import com.study.lovetoolbox.model.dto.menu.MenuDetailSaveMainDTO;
import com.study.lovetoolbox.model.entity.MenuDetail;
import com.study.lovetoolbox.model.vo.MenuDetailListVO;
import com.study.lovetoolbox.service.MenuDetailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * 菜单接口
 *
 */
@RestController
@RequestMapping("/menu-detail")
@Slf4j
public class MenuDetailController {

    @Resource
    private MenuDetailService menuDetailService;

    /**
     *  列表
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "列表", notes = "")
    public BaseResponse<List<MenuDetailListVO>> list(Long id) {
        return ResultUtils.success(menuDetailService.getList(id));
    }


    /**
     * 菜单接口 新增
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "修改", notes = "传入shoppingCart")
    public BaseResponse<Boolean> submit(@RequestBody MenuDetailSaveMainDTO menu) {
        menuDetailService.saveMenuDetail(menu);
        return ResultUtils.success(true);
    }

    /**
     * 菜单接口 修改
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "修改", notes = "传入shoppingCart")
    public BaseResponse<Boolean> update(@RequestBody MenuDetail menu) {
        return ResultUtils.success(menuDetailService.updateById(menu));
    }

    /**
     * 菜单接口 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    public BaseResponse<Boolean> remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return ResultUtils.success(menuDetailService.removeBatchByIds(Collections.singleton(ids)));
    }
}
