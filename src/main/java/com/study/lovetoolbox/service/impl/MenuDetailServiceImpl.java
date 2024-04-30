package com.study.lovetoolbox.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.lovetoolbox.constant.CommonConstant;
import com.study.lovetoolbox.model.dto.menu.MenuDetailSaveMainDTO;
import com.study.lovetoolbox.model.entity.MenuDetail;
import com.study.lovetoolbox.model.enums.NoticeTypeEnum;
import com.study.lovetoolbox.model.vo.MenuDetailListVO;
import com.study.lovetoolbox.mq.ProductMQ;
import com.study.lovetoolbox.service.MenuDetailService;
import com.study.lovetoolbox.mapper.MenuDetailMapper;
import com.study.lovetoolbox.utils.AuthUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author cy062
* @description 针对表【k_menu_detail(菜单表)】的数据库操作Service实现
* @createDate 2024-04-22 17:01:06
*/
@Service
public class MenuDetailServiceImpl extends ServiceImpl<MenuDetailMapper, MenuDetail>
    implements MenuDetailService{

    @Resource
    private ProductMQ productMQ;

    @Override
    public List<MenuDetailListVO> getList(Long id) {
        List<MenuDetail> list = list(Wrappers.<MenuDetail>query().lambda().eq(MenuDetail::getMenuId, id));
        return BeanUtil.copyToList(list, MenuDetailListVO.class);
    }

    @Override
    public void saveMenuDetail(MenuDetailSaveMainDTO dto) {
        if (ObjectUtils.isNotEmpty(dto) && ObjectUtils.isNotEmpty(dto.getMenuId())) {
            String type = CommonConstant.GENERATE;
            List<MenuDetail> list = list(Wrappers.<MenuDetail>query().lambda().eq(MenuDetail::getMenuId, dto.getMenuId()));
            if (CollectionUtils.isNotEmpty(list)) {
                type = (CommonConstant.UPDATE);
                removeBatchByIds(list);
            }
            List<MenuDetail> menuDetails = BeanUtil.copyToList(dto.getList(), MenuDetail.class);
            saveBatch(menuDetails);
            String message = dto.getMenuId().toString() + StringPool.COMMA + AuthUtils.getCurrentUser().getId() + StringPool.COMMA + AuthUtils.getCurrentUser().getRelationId() + StringPool.COMMA + type;
            productMQ.sendMessage(NoticeTypeEnum.MENU.getCode(), message);
        }
    }
}




