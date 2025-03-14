package com.ops.inventoryservice;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public boolean reserveStock(String product, int quantity) {
        InventoryItem item = inventoryRepository.findByProduct(product);
        if (item != null && item.getQuantityAvailable() >= quantity) {
            item.setQuantityAvailable(item.getQuantityAvailable() - quantity);
            inventoryRepository.save(item);
            return true;
        }
        return false;
    }
}