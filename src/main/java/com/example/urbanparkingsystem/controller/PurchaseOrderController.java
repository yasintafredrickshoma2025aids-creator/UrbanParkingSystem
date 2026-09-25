package com.example.urbanparkingsystem.controller;

import com.example.urbanparkingsystem.entity.PurchaseOrder;
import com.example.urbanparkingsystem.service.PurchaseOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(
            PurchaseOrderService purchaseOrderService) {

        this.purchaseOrderService = purchaseOrderService;
    }

    @PostMapping
    public PurchaseOrder createPurchaseOrder(
            @RequestBody PurchaseOrder purchaseOrder) {

        return purchaseOrderService.createPurchaseOrder(purchaseOrder);
    }

    @GetMapping
    public List<PurchaseOrder> getAllPurchaseOrders() {

        return purchaseOrderService.getAllPurchaseOrders();
    }

    @GetMapping("/{id}")
    public PurchaseOrder getPurchaseOrderById(
            @PathVariable Long id) {

        return purchaseOrderService.getPurchaseOrderById(id);
    }

    @PutMapping("/{id}/status")
    public PurchaseOrder updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return purchaseOrderService.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public String deletePurchaseOrder(
            @PathVariable Long id) {

        purchaseOrderService.deletePurchaseOrder(id);

        return "Purchase order deleted successfully";
    }
}