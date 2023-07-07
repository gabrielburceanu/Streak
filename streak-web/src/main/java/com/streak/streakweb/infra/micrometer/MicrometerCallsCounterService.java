package com.streak.streakweb.infra.micrometer;

import com.streak.streakweb.domain.CallsCounterService;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;


@Service
@AllArgsConstructor
public class MicrometerCallsCounterService implements CallsCounterService {
    private final MeterRegistry meterRegistry;
    private final Map<String, Integer> urlCount;

    @Override
    public synchronized void add(String url) {
        Integer count = urlCount.getOrDefault(url, 0);
        urlCount.put(url, count + 1);

        meterRegistry.counter("url:" + url).increment();
    }

    @Override
    public Map<String, Integer> getCalls() {
        return Collections.unmodifiableMap(urlCount);
    }
}
