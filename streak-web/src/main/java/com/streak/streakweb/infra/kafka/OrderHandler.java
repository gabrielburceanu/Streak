package com.streak.streakweb.infra.kafka;


import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.context.annotation.Profile;

@AllArgsConstructor
@Log4j2
@Profile("kafka")
public class OrderHandler implements BackgroundEventHandler {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;
    @Override
    public void onOpen() {
    }

    @Override
    public void onClosed() {

    }

    @Override
    public void onMessage(String event, MessageEvent messageEvent) throws Exception {
        log.info("Kafka Event: {}  messageEvent: {}", event, messageEvent);
        kafkaTemplate.send(topic, messageEvent.getData());
    }

    @Override
    public void onComment(String comment) {

    }

    @Override
    public void onError(Throwable t) {

    }
}
