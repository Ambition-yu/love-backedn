package com.study.lovetoolbox.model.dto.recipe;

import com.study.lovetoolbox.model.dto.common.Query;
import lombok.Data;

@Data
public class RecipeDetailListDTO extends Query {

    /**
     * 食谱id
     */
    private Long recipeId;
    /**
     * 分类id
     */
    private Long categoryId;
}
