package com.study.lovetoolbox.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.redis")
@Data
@Component
public class RedisProperties {

    private String host;

    private String port;
}
