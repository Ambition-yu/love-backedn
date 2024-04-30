package com.study.lovetoolbox.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.lovetoolbox.mapper.UserNoticeMapper;
import com.study.lovetoolbox.model.dto.notice.NoticePageDTO;
import com.study.lovetoolbox.model.entity.UserNotice;
import com.study.lovetoolbox.service.UserNoticeService;
import com.study.lovetoolbox.utils.AuthUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author cy062
* @description 针对表【user_notice(用户消息表)】的数据库操作Service实现
* @createDate 2024-04-18 15:15:56
*/
@Service
public class UserNoticeServiceImpl extends ServiceImpl<UserNoticeMapper, UserNotice>
    implements UserNoticeService {

    @Override
    public List<UserNotice> getUserNoticeList(Integer status) {
        return list(Wrappers.<UserNotice>query().lambda().eq(UserNotice::getUserId, AuthUtils.getCurrentUser().getId()).eq(UserNotice::getState, status));
    }

    @Override
    public IPage<UserNotice> getUserNoticePage(IPage page, NoticePageDTO dto) {
        return page(page, Wrappers.<UserNotice>query().lambda().eq(UserNotice::getUserId, AuthUtils.getCurrentUser().getId()).eq(UserNotice::getState, dto.getState()));
    }


}




