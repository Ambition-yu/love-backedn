package com.study.lovetoolbox.manager;

import com.study.lovetoolbox.common.ErrorCode;
import com.study.lovetoolbox.exception.BusinessException;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedissonManage {

    @Resource
    private RedissonClient redissonClient;

    public void createRedisson(String key) {
        // 根据key设置限流器
        RRateLimiter limiter = redissonClient.getRateLimiter(key);
        // 每分钟一次
        limiter.trySetRate(RateType.OVERALL, 1, 1, RateIntervalUnit.MINUTES);
        // 获取令牌 - 一次一个/可以根据角色进行动态配置
        boolean b = limiter.tryAcquire(1);
        if (!b) {
            throw new BusinessException(ErrorCode.FREQUENT_VISITS);
        }
    }
}
