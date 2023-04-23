package com.streak.streakclient;

import com.streak.streakclient.kafka.KafkaOrderConsumer;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("permitAll()")
@AllArgsConstructor
//@PreAuthorize("isAuthenticated()")
@Profile("kafka")
public class EventsController {
    KafkaOrderConsumer orderConsumer;

    @PreAuthorize("permitAll()")
    @GetMapping(path = "/show-orders")
    public List<String> showOrders() {
        return orderConsumer.getOrders();
    }

}
