package com.streak.streakweb.infra.redisson;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Configuration
public class RedissonConfiguration {
    @Bean
    public ConcurrentMap<String, String> createRedisMap(@Value("${redis.url}") String redisUrl) {

        try {
            log.debug("RedisUrl {}", redisUrl);
            //        // 1. Create config object
            Config config = new Config();
            config.useSingleServer()
                    //useClusterServers()
//                // use "rediss://" for SSL connection
                    .setAddress(redisUrl);
//
//        // Sync and Async API
            RedissonClient redisson = Redisson.create(config);
//
//        // 3. Get Redis based implementation of java.util.concurrent.ConcurrentMap
            return redisson.getMap("myRedisMap");
//
////// or read config from fileorg.redisson
////        config = Config.fromYAML(new File("config-file.yaml"));
        }
        catch (RedisConnectionException | IllegalArgumentException ex) {
            log.error("Redis failed to connect, using mock");
            return new ConcurrentHashMap<>();
        }
    }

}
