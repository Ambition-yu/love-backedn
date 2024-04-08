package com.study.lovetoolbox.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.study.lovetoolbox.common.BaseResponse;
import com.study.lovetoolbox.common.ResultUtils;
import com.study.lovetoolbox.model.entity.Menu;
import com.study.lovetoolbox.service.MenuService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * 菜单接口
 *
 */
@RestController
@RequestMapping("/menu")
@Slf4j
public class MenuController {

    @Resource
    private MenuService menuService;


    /**
     * 菜单接口 新增
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "修改", notes = "传入shoppingCart")
    public BaseResponse<Boolean> submit(@RequestBody Menu menu) {
        return ResultUtils.success(menuService.save(menu));
    }

    /**
     * 菜单接口 修改
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "修改", notes = "传入shoppingCart")
    public BaseResponse<Boolean> update(@RequestBody Menu menu) {
        return ResultUtils.success(menuService.updateById(menu));
    }

    /**
     * 菜单接口 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    public BaseResponse<Boolean> remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return ResultUtils.success(menuService.removeBatchByIds(Collections.singleton(ids)));
    }
}
