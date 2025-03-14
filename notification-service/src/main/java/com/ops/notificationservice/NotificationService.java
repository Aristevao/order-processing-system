package com.ops.notificationservice;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificationService {
    public void sendNotification(PaymentDTO payment) {
        if ("APPROVED".equals(payment.getPaymentStatus())) {
            log.info("✅ Pagamento aprovado! Enviando notificação para {} sobre o pedido {}.",
                    payment.getCustomerName(), payment.getOrderId());
        } else {
            log.warn("❌ Pagamento rejeitado! Notificando {} sobre a falha no pedido {}.",
                    payment.getCustomerName(), payment.getOrderId());
        }
    }
}
