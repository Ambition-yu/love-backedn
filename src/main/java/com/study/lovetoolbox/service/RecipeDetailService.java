package com.study.lovetoolbox.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.study.lovetoolbox.model.dto.recipe.RecipeDetailDTO;
import com.study.lovetoolbox.model.dto.recipe.RecipeDetailListDTO;
import com.study.lovetoolbox.model.entity.RecipeDetail;
import com.study.lovetoolbox.model.vo.RecipeDetailVO;

import java.util.List;

/**
* @author cy062
* @description 针对表【k_recipe_detail(食谱明细表)】的数据库操作Service
* @createDate 2024-04-08 15:42:35
*/
public interface RecipeDetailService extends IService<RecipeDetail> {

    /**
     * 保存食物
     *
     * @param dto
     * @return
     */
    boolean saveRecipeDetail(RecipeDetailDTO dto);

    Page<RecipeDetailVO> getList(RecipeDetailListDTO dto);

}
