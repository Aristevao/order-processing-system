package com.ops.notificationservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "payment-processed", groupId = "notification-group")
    public void consumePaymentEvent(String message) {
        log.info("Recebido evento de pagamento: {}", message);
        try {
            PaymentDTO payment = objectMapper.readValue(message, PaymentDTO.class);
            notificationService.sendNotification(payment);
        } catch (JsonProcessingException e) {
            log.error("Erro ao processar evento de pagamento", e);
        }
    }
}