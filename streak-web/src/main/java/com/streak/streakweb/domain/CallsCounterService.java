package com.streak.streakweb.domain;

import java.util.Map;

public interface CallsCounterService {
    void add(String url);

    Map<String, Integer> getCalls();
}
