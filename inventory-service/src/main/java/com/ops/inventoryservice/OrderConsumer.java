package com.ops.inventoryservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderConsumer {

    private final InventoryService inventoryService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "order-created", groupId = "inventory-group")
    public void consumeOrderEvent(String message) {
        log.info("Recebido novo pedido: {}", message);
        try {
            OrderDTO order = objectMapper.readValue(message, OrderDTO.class);

            boolean stockReserved = inventoryService.reserveStock(order.getProduct(), order.getQuantity());
            if (stockReserved) {
                log.info("Estoque reservado para o produto: {}", order.getProduct());
            } else {
                log.warn("Estoque insuficiente para o produto: {}", order.getProduct());
            }
        } catch (JsonProcessingException e) {
            log.error("Erro ao processar evento de pedido", e);
        }
    }
}
