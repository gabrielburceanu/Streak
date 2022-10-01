package com.streak.streakclient;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
//import org.redisson.*;

@Component
public class RedissonService {
    ConcurrentMap<String, String> map;
    public RedissonService() {
        try {
            initRedis();
        } catch(RedisConnectionException ex) {
            map = new ConcurrentHashMap<>();
        }
    }

    private void initRedis() {

        //        // 1. Create config object
        Config config = new Config();
        config.useSingleServer()
                //useClusterServers()
//                // use "rediss://" for SSL connection
                .setAddress("redis://localhost:6379");
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

    void push(String key, String value) {
        map.put(key, value);
    }

    String get(String key) {
        return map.get(key);
    }
}