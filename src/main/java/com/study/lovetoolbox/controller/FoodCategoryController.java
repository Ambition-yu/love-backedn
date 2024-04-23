package com.study.lovetoolbox.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.study.lovetoolbox.common.BaseResponse;
import com.study.lovetoolbox.common.ResultUtils;
import com.study.lovetoolbox.model.dto.foodcategory.FoodCategoryDTO;
import com.study.lovetoolbox.model.dto.recipe.RecipeDTO;
import com.study.lovetoolbox.model.entity.FoodCategory;
import com.study.lovetoolbox.model.entity.Recipe;
import com.study.lovetoolbox.service.FoodCategoryService;
import com.study.lovetoolbox.service.RecipeDetailService;
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
@RequestMapping("/food-category")
@Slf4j
public class FoodCategoryController {

    @Resource
    private FoodCategoryService foodCategoryService;

    /**
     *  列表
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "列表", notes = "食谱id")
    public BaseResponse<List<String>> list(Long id) {
        return ResultUtils.success(foodCategoryService.getList(id));
    }


    /**
     * 分类接口 新增
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "保存", notes = "")
    public BaseResponse<Boolean> submit(@RequestBody FoodCategoryDTO dto) {
        return ResultUtils.success(foodCategoryService.saveFoodCategory(dto));
    }

    /**
     * 食谱接口 修改
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "修改", notes = "")
    public BaseResponse<Boolean> update(@RequestBody FoodCategory foodCategory) {
        return ResultUtils.success(foodCategoryService.updateById(foodCategory));
    }

    /**
     * 食谱接口 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    public BaseResponse<Boolean> remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return ResultUtils.success(foodCategoryService.removeBatchByIds(Collections.singleton(ids)));
    }
}
