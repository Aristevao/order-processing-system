package com.ops.orderservice;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHistoryRepository extends CassandraRepository<OrderHistory, Long> {
}
