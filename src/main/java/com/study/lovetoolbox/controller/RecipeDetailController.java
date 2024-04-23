package com.study.lovetoolbox.controller;

import cn.hutool.db.sql.Query;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.study.lovetoolbox.common.BaseResponse;
import com.study.lovetoolbox.common.ResultUtils;
import com.study.lovetoolbox.model.dto.recipe.RecipeDetailDTO;
import com.study.lovetoolbox.model.dto.recipe.RecipeDetailListDTO;
import com.study.lovetoolbox.model.entity.RecipeDetail;
import com.study.lovetoolbox.model.vo.RecipeDetailVO;
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
@RequestMapping("/recipe-detail")
@Slf4j
public class RecipeDetailController {

    @Resource
    private RecipeDetailService recipeDetailService;

    /**
     * 食谱接口 分页列表
     */
    @PostMapping("/list")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "列表", notes = "")
    public BaseResponse<Page<RecipeDetailVO>> list(RecipeDetailListDTO dto) {
        return ResultUtils.success(recipeDetailService.getList(dto));
    }


    /**
     * 食谱接口 新增
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "保存", notes = "")
    public BaseResponse<Boolean> submit(@RequestBody RecipeDetailDTO dto) {
        return ResultUtils.success(recipeDetailService.saveRecipeDetail(dto));
    }

    /**
     * 食谱接口 修改
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "修改", notes = "")
    public BaseResponse<Boolean> update(@RequestBody RecipeDetail recipeDetail) {
        return ResultUtils.success(recipeDetailService.updateById(recipeDetail));
    }

    /**
     * 食谱接口 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    public BaseResponse<Boolean> remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return ResultUtils.success(recipeDetailService.removeBatchByIds(Collections.singleton(ids)));
    }
}
