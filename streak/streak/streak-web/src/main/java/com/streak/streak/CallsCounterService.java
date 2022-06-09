package com.streak.streak;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class CallsCounterService {
    Map<String, Integer> urlCount = new ConcurrentHashMap<>();

    private final MeterRegistry meterRegistry;

    public CallsCounterService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public synchronized void add(String url) {
        Integer count = urlCount.getOrDefault(url, 0);
        urlCount.put(url, count + 1);

        meterRegistry.counter("url:" + url).increment();
    }

    public Map<String, Integer> getCalls() {
        return Collections.unmodifiableMap(urlCount);
    }
}
