package com.streak.streakclient.infra.redisson;

import com.streak.streakclient.domain.DistributedMapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentMap;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RedissonRepository implements DistributedMapRepository {

    private final ConcurrentMap<String, String> map;

    @Override
    public void put(String key, String value) {
        map.put(key, value);
    }

    @Override
    public String get(String key) {
        return map.get(key);
    }
}