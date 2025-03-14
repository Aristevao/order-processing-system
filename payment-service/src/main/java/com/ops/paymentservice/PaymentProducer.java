package com.ops.paymentservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final String TOPIC = "payment-processed";

    public void sendPaymentEvent(PaymentDTO paymentDTO) {
        try {
            String message = objectMapper.writeValueAsString(paymentDTO);
            kafkaTemplate.send(TOPIC, message);
            log.info("Evento de pagamento enviado: {}", message);
        } catch (JsonProcessingException e) {
            log.error("Erro ao serializar evento de pagamento", e);
        }
    }
}
