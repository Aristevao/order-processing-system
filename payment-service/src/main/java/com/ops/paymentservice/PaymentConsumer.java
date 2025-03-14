package com.ops.paymentservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentConsumer {

    private final PaymentService paymentService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "order-created", groupId = "payment-group")
    public void consumeOrderEvent(String message) {
        log.info("Recebendo pedido para pagamento: {}", message);
        try {
            OrderDTO order = objectMapper.readValue(message, OrderDTO.class);
            paymentService.processPayment(order);
        } catch (JsonProcessingException e) {
            log.error("Erro ao processar evento de pedido", e);
        }
    }
}
