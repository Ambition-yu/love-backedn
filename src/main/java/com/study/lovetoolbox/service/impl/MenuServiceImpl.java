package com.study.lovetoolbox.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.lovetoolbox.mapper.MenuMapper;
import com.study.lovetoolbox.model.dto.menu.MenuSaveDTO;
import com.study.lovetoolbox.model.entity.Menu;
import com.study.lovetoolbox.model.vo.MenuListVO;
import com.study.lovetoolbox.service.MenuService;
import com.study.lovetoolbox.utils.AuthUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author cy062
* @description 针对表【k_menu(菜单表)】的数据库操作Service实现
* @createDate 2024-04-08 15:42:35
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

    @Override
    public boolean saveMenu(MenuSaveDTO dto) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(dto, menu);
        menu.setCreateUser(AuthUtils.getCurrentUser().getId());
        return save(menu);
    }

    @Override
    public List<MenuListVO> menuList() {
        return baseMapper.menuList(AuthUtils.getCurrentUserAndRelationUser(), 1);
    }
}




