package com.streak.streakclient.infra.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;

@Slf4j
@Service
@Profile("kafka")
@AllArgsConstructor
public class KafkaOrderConsumer {

    private final Queue<String> orderQueue;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(String eventMessage){
        log.info(String.format("Event message received -> %s", eventMessage));
        orderQueue.add(eventMessage);
    }

    public List<String> getOrders() {
        return orderQueue.stream().toList();
    }
}
