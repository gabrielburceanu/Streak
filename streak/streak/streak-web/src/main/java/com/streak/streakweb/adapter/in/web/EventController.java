package com.streak.streakweb.adapter.in.web;

import com.streak.streakweb.kafka.OrderEventProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@Profile("kafka")
public class EventController {
    private final OrderEventProducer kafkaProducer;

    @Value("${spring.kafka.topic.name}")
    private String ordersTopic;
    @GetMapping(path = "/order-kafka")
    public String orderKafka() {
        kafkaProducer.sendKafkaEvent(ordersTopic, "fix", "my-code");

        return "done";
    }
}