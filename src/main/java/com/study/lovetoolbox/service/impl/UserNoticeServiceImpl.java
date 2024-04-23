package com.study.lovetoolbox.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.lovetoolbox.mapper.UserNoticeMapper;
import com.study.lovetoolbox.model.entity.UserNotice;
import com.study.lovetoolbox.service.UserNoticeService;
import org.springframework.stereotype.Service;

/**
* @author cy062
* @description 针对表【user_notice(用户消息表)】的数据库操作Service实现
* @createDate 2024-04-18 15:15:56
*/
@Service
public class UserNoticeServiceImpl extends ServiceImpl<UserNoticeMapper, UserNotice>
    implements UserNoticeService {

}




