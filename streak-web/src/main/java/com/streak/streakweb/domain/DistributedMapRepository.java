package com.streak.streakweb.domain;

public interface DistributedMapRepository {
    void put(String key, String value);

    String get(String key);
}
