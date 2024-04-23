package com.study.lovetoolbox.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.lovetoolbox.common.ErrorCode;
import com.study.lovetoolbox.exception.ThrowUtils;
import com.study.lovetoolbox.mapper.RecipeDetailMapper;
import com.study.lovetoolbox.model.dto.recipe.RecipeDetailDTO;
import com.study.lovetoolbox.model.dto.recipe.RecipeDetailListDTO;
import com.study.lovetoolbox.model.entity.RecipeDetail;
import com.study.lovetoolbox.model.vo.RecipeDetailVO;
import com.study.lovetoolbox.service.RecipeDetailService;
import com.study.lovetoolbox.utils.AuthUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author cy062
* @description 针对表【k_recipe_detail(食谱明细表)】的数据库操作Service实现
* @createDate 2024-04-08 15:42:35
*/
@Service
public class RecipeDetailServiceImpl extends ServiceImpl<RecipeDetailMapper, RecipeDetail>
    implements RecipeDetailService{

    @Override
    public boolean saveRecipeDetail(RecipeDetailDTO dto) {
        ThrowUtils.throwIf(ObjectUtils.isEmpty(dto.getRecipeId()) || ObjectUtils.isNotEmpty(dto.getFoodCategoryId()), ErrorCode.PARAMS_ERROR, "该食谱/分类不存在");
        RecipeDetail recipeDetail = new RecipeDetail();;
        BeanUtils.copyProperties(dto, recipeDetail);
        recipeDetail.setUserId(AuthUtils.getCurrentUser().getId());
        return save(recipeDetail);
    }

    @Override
    public Page<RecipeDetailVO> getList(RecipeDetailListDTO dto) {
        Page<RecipeDetailVO> result = new Page<>(dto.getCurrent(), dto.getSize());
        List<RecipeDetail> list = list(Wrappers.<RecipeDetail>query().lambda().eq(RecipeDetail::getRecipeId, dto.getCategoryId()).eq(RecipeDetail::getFoodCategoryId, dto.getCategoryId()));
        List<RecipeDetailVO> voList = BeanUtil.copyToList(list, RecipeDetailVO.class);
        result.setRecords(voList);
        return result;
    }
}




