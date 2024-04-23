package com.study.lovetoolbox.service;

import com.study.lovetoolbox.model.dto.menu.MenuSaveDTO;
import com.study.lovetoolbox.model.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.study.lovetoolbox.model.vo.MenuListVO;

import java.util.List;

/**
* @author cy062
* @description 针对表【k_menu(菜单表)】的数据库操作Service
* @createDate 2024-04-08 15:42:35
*/
public interface MenuService extends IService<Menu> {

    boolean saveMenu(MenuSaveDTO dto);

    List<MenuListVO> menuList();

}
