package com.example.urbanparkingsystem.service;

import com.example.urbanparkingsystem.entity.PurchaseOrder;
import com.example.urbanparkingsystem.repository.PurchaseOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderService(
            PurchaseOrderRepository purchaseOrderRepository) {

        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    public PurchaseOrder createPurchaseOrder(
            PurchaseOrder purchaseOrder) {

        if (purchaseOrder.getOrderDate() == null) {
            purchaseOrder.setOrderDate(LocalDate.now());
        }

        if (purchaseOrder.getStatus() == null) {
            purchaseOrder.setStatus("CREATED");
        }

        return purchaseOrderRepository.save(purchaseOrder);
    }

    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    public PurchaseOrder getPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id).orElse(null);
    }

    public PurchaseOrder updateStatus(Long id, String status) {

        PurchaseOrder purchaseOrder =
                purchaseOrderRepository.findById(id).orElse(null);

        if (purchaseOrder == null) {
            return null;
        }

        purchaseOrder.setStatus(status);

        return purchaseOrderRepository.save(purchaseOrder);
    }

    public void deletePurchaseOrder(Long id) {
        purchaseOrderRepository.deleteById(id);
    }
}