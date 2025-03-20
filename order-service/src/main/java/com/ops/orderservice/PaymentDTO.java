package com.ops.orderservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long orderId;
    private String customerName;
    private String product;
    private Integer quantity;
    private String paymentStatus;
}
