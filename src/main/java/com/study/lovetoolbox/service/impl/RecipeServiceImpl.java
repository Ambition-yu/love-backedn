package com.study.lovetoolbox.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.lovetoolbox.model.entity.Recipe;
import com.study.lovetoolbox.service.RecipeService;
import com.study.lovetoolbox.mapper.RecipeMapper;
import org.springframework.stereotype.Service;

/**
* @author cy062
* @description 针对表【k_recipe(食谱表)】的数据库操作Service实现
* @createDate 2024-04-08 15:42:35
*/
@Service
public class RecipeServiceImpl extends ServiceImpl<RecipeMapper, Recipe>
    implements RecipeService{

}




