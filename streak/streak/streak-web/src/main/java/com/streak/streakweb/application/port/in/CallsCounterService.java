package com.streak.streakweb.application.port.in;

import java.util.Map;

public interface CallsCounterService {
    void add(String url);

    Map<String, Integer> getCalls();
}