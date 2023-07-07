package com.streak.streakweb.infra.micrometer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Configuration
public class MicrometerConfiguration {
    @Bean
    public Map<String, Integer> createUrlCountMap() {
        return new ConcurrentHashMap<>();
    }
}
