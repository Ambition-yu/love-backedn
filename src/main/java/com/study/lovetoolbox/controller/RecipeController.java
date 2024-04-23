package com.study.lovetoolbox.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.study.lovetoolbox.common.BaseResponse;
import com.study.lovetoolbox.common.ResultUtils;
import com.study.lovetoolbox.model.dto.recipe.RecipeDTO;
import com.study.lovetoolbox.model.entity.Recipe;
import com.study.lovetoolbox.service.RecipeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * 食谱接口
 *
 */
@RestController
@RequestMapping("/recipe")
@Slf4j
public class RecipeController {

    @Resource
    private RecipeService recipeService;

    /**
     * 食谱接口 列表
     */
    @PostMapping("/list")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "列表", notes = "")
    public BaseResponse<List<String>> list() {
        return ResultUtils.success(recipeService.getList());
    }


    /**
     * 食谱接口 新增
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "保存", notes = "")
    public BaseResponse<Boolean> submit(@RequestBody RecipeDTO dto) {
        return ResultUtils.success(recipeService.saveRecipe(dto));
    }

    /**
     * 食谱接口 修改
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "修改", notes = "")
    public BaseResponse<Boolean> update(@RequestBody Recipe recipe) {
        return ResultUtils.success(recipeService.updateById(recipe));
    }

    /**
     * 食谱接口 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    public BaseResponse<Boolean> remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return ResultUtils.success(recipeService.removeBatchByIds(Collections.singleton(ids)));
    }
}
