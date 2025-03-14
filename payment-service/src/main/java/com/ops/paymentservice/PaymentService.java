package com.ops.paymentservice;

import java.util.Random;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentProducer paymentProducer;

    public void processPayment(OrderDTO order) {
        boolean paymentApproved = new Random().nextBoolean();
        String status = paymentApproved ? "APPROVED" : "REJECTED";

        log.info("Pagamento {} para o pedido {}", status, order.getId());

        PaymentDTO paymentDTO = new PaymentDTO(order.getId(), order.getCustomerName(), order.getProduct(), order.getQuantity(), status);
        paymentProducer.sendPaymentEvent(paymentDTO);
    }
}
