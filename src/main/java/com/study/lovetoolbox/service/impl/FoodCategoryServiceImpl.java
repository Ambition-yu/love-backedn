package com.study.lovetoolbox.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.lovetoolbox.model.entity.FoodCategory;
import com.study.lovetoolbox.service.FoodCategoryService;
import com.study.lovetoolbox.mapper.FoodCategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author cy062
* @description 针对表【k_food_category(菜品分类表)】的数据库操作Service实现
* @createDate 2024-04-08 15:42:34
*/
@Service
public class FoodCategoryServiceImpl extends ServiceImpl<FoodCategoryMapper, FoodCategory>
    implements FoodCategoryService{

}




