package com.study.lovetoolbox.model.dto.recipe;

import lombok.Data;

@Data
public class RecipeDetailListDTO {

    /**
     * 食谱id
     */
    private Long recipeId;
    /**
     * 分类id
     */
    private Long categoryId;

    private long current;
    private long size;

    {
        current = 1;
        size = 10;
    }
}
