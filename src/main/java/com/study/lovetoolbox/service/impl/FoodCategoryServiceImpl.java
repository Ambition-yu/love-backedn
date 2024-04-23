package com.study.lovetoolbox.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.lovetoolbox.common.ErrorCode;
import com.study.lovetoolbox.exception.ThrowUtils;
import com.study.lovetoolbox.model.dto.foodcategory.FoodCategoryDTO;
import com.study.lovetoolbox.model.entity.FoodCategory;
import com.study.lovetoolbox.service.FoodCategoryService;
import com.study.lovetoolbox.mapper.FoodCategoryMapper;
import com.study.lovetoolbox.utils.AuthUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author cy062
* @description 针对表【k_food_category(菜品分类表)】的数据库操作Service实现
* @createDate 2024-04-08 15:42:34
*/
@Service
public class FoodCategoryServiceImpl extends ServiceImpl<FoodCategoryMapper, FoodCategory>
    implements FoodCategoryService{

    @Override
    public boolean saveFoodCategory(FoodCategoryDTO dto) {
        ThrowUtils.throwIf(ObjectUtils.isEmpty(dto.getRecipeId()), ErrorCode.PARAMS_ERROR, "食谱不存在");
        FoodCategory foodCategory = new FoodCategory();
        BeanUtils.copyProperties(dto, foodCategory);
        foodCategory.setCreateUser(AuthUtils.getCurrentUser().getId());
        return save(foodCategory);
    }

    @Override
    public List<String> getList(Long id) {
        List<FoodCategory> list = list(Wrappers.<FoodCategory>query().lambda().eq(FoodCategory::getRecipeId, id).select(FoodCategory::getFoodName));
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().map(FoodCategory::getFoodName).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}




