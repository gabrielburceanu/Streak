package com.streak.streakclient.kafka;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;


@Profile("Test")
@ExtendWith(MockitoExtension.class)
class KafkaOrderConsumerTest {

    @InjectMocks
    private KafkaOrderConsumer kafkaOrderConsumer;
    @Mock
    private Queue<String> mockQueue;

    @Test
    void getOrders() {
        doReturn(true).when(mockQueue).add(any());
        kafkaOrderConsumer.consume("KafkaMessage1");
        doReturn(Stream.of("KafkaMessage1")).when(mockQueue).stream();
        List<String> orders = kafkaOrderConsumer.getOrders();
        System.out.println(orders + "");
    }
}