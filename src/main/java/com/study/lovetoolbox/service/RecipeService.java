package com.study.lovetoolbox.service;

import com.study.lovetoolbox.model.dto.recipe.RecipeDTO;
import com.study.lovetoolbox.model.entity.Recipe;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author cy062
* @description 针对表【k_recipe(食谱表)】的数据库操作Service
* @createDate 2024-04-08 15:42:35
*/
public interface RecipeService extends IService<Recipe> {

    /**
     * 保存食谱
     *
     * @param dto
     * @return
     */
    boolean saveRecipe(RecipeDTO dto);

    /**
     * 获取食谱列表
     *
     * @return
     */
    List<String> getList();
}
