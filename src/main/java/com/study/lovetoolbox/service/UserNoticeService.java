package com.study.lovetoolbox.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.study.lovetoolbox.model.dto.notice.NoticePageDTO;
import com.study.lovetoolbox.model.entity.UserNotice;

import java.util.List;

/**
* @author cy062
* @description 针对表【user_notice(用户消息表)】的数据库操作Service
* @createDate 2024-04-18 15:15:56
*/
public interface UserNoticeService extends IService<UserNotice> {


    List<UserNotice> getUserNoticeList(Integer status);

    IPage<UserNotice> getUserNoticePage(IPage page, NoticePageDTO dto);

}
