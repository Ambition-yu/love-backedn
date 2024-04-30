package com.study.lovetoolbox.service;

import com.study.lovetoolbox.model.dto.menu.MenuDetailSaveMainDTO;
import com.study.lovetoolbox.model.entity.MenuDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.study.lovetoolbox.model.vo.MenuDetailListVO;

import java.util.List;

/**
* @author cy062
* @description 针对表【k_menu_detail(菜单表)】的数据库操作Service
* @createDate 2024-04-22 17:01:06
*/
public interface MenuDetailService extends IService<MenuDetail> {

    List<MenuDetailListVO> getList(Long id);

    void saveMenuDetail(MenuDetailSaveMainDTO dto);

}
