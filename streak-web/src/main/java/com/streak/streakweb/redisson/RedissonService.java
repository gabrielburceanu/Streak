package com.streak.streakweb.redisson;

import com.streak.streakweb.application.port.in.DistributedMapService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Service
public class RedissonService implements DistributedMapService {

    private final String redisUrl;
    private ConcurrentMap<String, String> map;

    @Autowired
    public RedissonService(@Value("${redis.url}") String redisUrl) {
        this.redisUrl = redisUrl;
        try {
            initRedis();
        } catch (RedisConnectionException ex) {
            log.error("Redis failed to connect, using mock");
            map = new ConcurrentHashMap<>();
        }
    }

    private void initRedis() {

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
        map = redisson.getMap("myRedisMap");
//
////// or read config from fileorg.redisson
////        config = Config.fromYAML(new File("config-file.yaml"));
    }

    @Override
    public void put(String key, String value) {
        map.put(key, value);
    }

    @Override
    public String get(String key) {
        return map.get(key);
    }
}