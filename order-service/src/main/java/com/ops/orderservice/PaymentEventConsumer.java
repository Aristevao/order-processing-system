package com.ops.orderservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentEventConsumer {

    private final OrderHistoryRepository orderHistoryRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "payment-processed", groupId = "order-group")
    public void consumePaymentEvent(String message) {
        try {
            PaymentDTO payment = objectMapper.readValue(message, PaymentDTO.class);
            if ("APPROVED".equals(payment.getPaymentStatus())) {
                OrderHistory order = new OrderHistory(payment.getOrderId(), payment.getCustomerName(),
                        payment.getProduct(), payment.getQuantity(), payment.getPaymentStatus());
                orderHistoryRepository.save(order);
                log.info("✅ Pedido salvo no Cassandra: {}", order);
            }
        } catch (Exception e) {
            log.error("Erro ao salvar pedido no Cassandra", e);
        }
    }
}
