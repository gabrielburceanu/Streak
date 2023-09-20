package com.streak.streakclient.applicationservice;

import com.streak.streakclient.infra.kafka.KafkaOrderConsumer;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Profile("kafka")
public class EventsController {
    KafkaOrderConsumer orderConsumer;

    @GetMapping(path = "/show-orders")
    public List<String> showOrders() {
        return orderConsumer.getOrders();
    }

}
