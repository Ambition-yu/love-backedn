package com.study.lovetoolbox.mapper;

import com.study.lovetoolbox.model.entity.Recipe;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author cy062
* @description 针对表【k_recipe(食谱表)】的数据库操作Mapper
* @createDate 2024-04-08 15:42:35
* @Entity com.study.lovetoolbox.model.entity.Recipe
*/
public interface RecipeMapper extends BaseMapper<Recipe> {

    List<String> getList(@Param("ids") List<Long> ids);

}




