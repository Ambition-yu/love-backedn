package com.study.lovetoolbox.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.lovetoolbox.model.entity.Menu;
import com.study.lovetoolbox.model.vo.MenuListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author cy062
* @description 针对表【k_menu(菜单表)】的数据库操作Mapper
* @createDate 2024-04-08 15:42:35
* @Entity com.study.lovetoolbox.model.entity.Menu
*/
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     *
     * @param ids
     * @param flag 1 当前 2 历史
     * @return
     */
    List<MenuListVO> menuList(@Param("ids") List<Long> ids, @Param("flag") Integer flag);

}




