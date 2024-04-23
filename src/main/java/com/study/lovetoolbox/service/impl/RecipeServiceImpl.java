package com.study.lovetoolbox.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.lovetoolbox.model.dto.recipe.RecipeDTO;
import com.study.lovetoolbox.model.entity.Recipe;
import com.study.lovetoolbox.model.vo.UserVO;
import com.study.lovetoolbox.service.RecipeService;
import com.study.lovetoolbox.mapper.RecipeMapper;
import com.study.lovetoolbox.utils.AuthUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author cy062
* @description 针对表【k_recipe(食谱表)】的数据库操作Service实现
* @createDate 2024-04-08 15:42:35
*/
@Service
public class RecipeServiceImpl extends ServiceImpl<RecipeMapper, Recipe>
    implements RecipeService{

    @Override
    public boolean saveRecipe(RecipeDTO dto) {
        Recipe recipe = new Recipe();
        BeanUtils.copyProperties(dto, recipe);
        recipe.setUserId(ObjectUtils.requireNonEmpty(AuthUtils.getCurrentUser()).getId());
        return save(recipe);
    }

    @Override
    public List<String> getList() {
        return baseMapper.getList(AuthUtils.getCurrentUserAndRelationUser());
    }
}




