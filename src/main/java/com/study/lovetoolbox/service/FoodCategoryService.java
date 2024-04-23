package com.study.lovetoolbox.service;

import com.study.lovetoolbox.model.dto.foodcategory.FoodCategoryDTO;
import com.study.lovetoolbox.model.entity.FoodCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author cy062
* @description 针对表【k_food_category(菜品分类表)】的数据库操作Service
* @createDate 2024-04-08 15:42:34
*/
public interface FoodCategoryService extends IService<FoodCategory> {

    /**
     * 保存食谱分类
     *
     * @param dto
     * @return
     */
    boolean saveFoodCategory(FoodCategoryDTO dto);

    /**
     * 获取食谱分类列表
     *
     * @return
     * @param id
     */
    List<String> getList(Long id);


}
