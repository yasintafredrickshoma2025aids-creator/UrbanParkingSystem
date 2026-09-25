package com.example.urbanparkingsystem.controller;

import com.example.urbanparkingsystem.entity.ParkingInvoice;
import com.example.urbanparkingsystem.service.ParkingInvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-invoices")
public class ParkingInvoiceController {

    private final ParkingInvoiceService parkingInvoiceService;

    public ParkingInvoiceController(ParkingInvoiceService parkingInvoiceService) {
        this.parkingInvoiceService = parkingInvoiceService;
    }

    // Create invoice
    @PostMapping
    public ParkingInvoice createInvoice(
            @RequestBody ParkingInvoice invoice) {

        return parkingInvoiceService.createInvoice(invoice);
    }

    // Get all invoices
    @GetMapping
    public List<ParkingInvoice> getAllInvoices() {

        return parkingInvoiceService.getAllInvoices();
    }

    // Get invoice by ID
    @GetMapping("/{id}")
    public ParkingInvoice getInvoiceById(
            @PathVariable Long id) {

        return parkingInvoiceService.getInvoiceById(id);
    }

    // Mark invoice as paid
    @PostMapping("/{id}/pay")
    public ParkingInvoice markAsPaid(
            @PathVariable Long id) {

        return parkingInvoiceService.markAsPaid(id);
    }
}