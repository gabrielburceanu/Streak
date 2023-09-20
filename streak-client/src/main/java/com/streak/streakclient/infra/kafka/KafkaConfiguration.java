package com.streak.streakclient.infra.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Configuration
public class KafkaConfiguration {

    @Bean
    public Queue<String> createQueueBean() {
        return new ConcurrentLinkedQueue<>();
    }
}
