package com.streak.streakweb.application.port.in;

public interface DistributedMapService {
    void put(String key, String value);

    String get(String key);
}
