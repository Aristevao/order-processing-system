package com.ops.orderservice;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("order_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistory {

    @PrimaryKey
    private Long orderId;
    private String customerName;
    private String product;
    private Integer quantity;
    private String paymentStatus;
}
