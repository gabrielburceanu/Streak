package com.streak.streakweb.kafka;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Service
@Profile("kafka")
public class OrderEventProducer {
    KafkaTemplate<String, String> kafkaTemplate;

    public void sendKafkaEvent(String topic, String key, String value) {
        BackgroundEventHandler eventHandler = new OrderHandler(kafkaTemplate, topic);
        kafkaTemplate.send(topic, key, value);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        EventSource.Builder builder = new EventSource.Builder(URI.create(url));
        BackgroundEventSource.Builder backgroundBuilder = new BackgroundEventSource.Builder(eventHandler, builder);
        try (BackgroundEventSource eventSource = backgroundBuilder.build()) {
            eventSource.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }



    }

}
