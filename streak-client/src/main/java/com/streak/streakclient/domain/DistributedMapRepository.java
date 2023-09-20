package com.streak.streakclient.domain;

public interface DistributedMapRepository {
    void put(String key, String value);

    String get(String key);
}
