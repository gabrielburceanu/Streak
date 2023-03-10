package com.streak.streakclient.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Service
public class KafkaOrderConsumer {

    Queue<String> orderQueue = new ConcurrentLinkedQueue<>();

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
