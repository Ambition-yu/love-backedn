package com.study.lovetoolbox.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.lovetoolbox.model.dto.menu.MenuDetailSaveDTO;
import com.study.lovetoolbox.model.entity.MenuDetail;
import com.study.lovetoolbox.model.vo.MenuDetailListVO;
import com.study.lovetoolbox.service.MenuDetailService;
import com.study.lovetoolbox.mapper.MenuDetailMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author cy062
* @description 针对表【k_menu_detail(菜单表)】的数据库操作Service实现
* @createDate 2024-04-22 17:01:06
*/
@Service
public class MenuDetailServiceImpl extends ServiceImpl<MenuDetailMapper, MenuDetail>
    implements MenuDetailService{

    @Override
    public List<MenuDetailListVO> getList(Long id) {
        List<MenuDetail> list = list(Wrappers.<MenuDetail>query().lambda().eq(MenuDetail::getMenuId, id));
        return BeanUtil.copyToList(list, MenuDetailListVO.class);
    }

    @Override
    public void saveMenuDetail(List<MenuDetailSaveDTO> dto) {

    }
}




