package com.ops.orderservice;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "order-created";

    public void sendOrderEvent(String orderJson) {
        kafkaTemplate.send(TOPIC, orderJson);
    }
}