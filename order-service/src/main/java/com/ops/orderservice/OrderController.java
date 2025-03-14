package com.ops.orderservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProducer orderProducer;
    private final ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO order) {
        try {
            String orderJson = objectMapper.writeValueAsString(order);
            orderProducer.sendOrderEvent(orderJson);
            return ResponseEntity.ok("Pedido enviado para processamento.");
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar pedido.");
        }
    }

    @GetMapping
    public ResponseEntity<String> greeting() {
        log.info("Received request: GET /orders");
        return ResponseEntity.ok("Hello World");
    }
}