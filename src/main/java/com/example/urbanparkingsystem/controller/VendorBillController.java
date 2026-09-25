package com.example.urbanparkingsystem.controller;

import com.example.urbanparkingsystem.entity.VendorBill;
import com.example.urbanparkingsystem.service.VendorBillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor-bills")
public class VendorBillController {

    private final VendorBillService vendorBillService;

    public VendorBillController(VendorBillService vendorBillService) {
        this.vendorBillService = vendorBillService;
    }

    @PostMapping
    public VendorBill createVendorBill(
            @RequestBody VendorBill vendorBill) {

        return vendorBillService.createVendorBill(vendorBill);
    }

    @GetMapping
    public List<VendorBill> getAllVendorBills() {
        return vendorBillService.getAllVendorBills();
    }

    @GetMapping("/{id}")
    public VendorBill getVendorBillById(
            @PathVariable Long id) {

        return vendorBillService.getVendorBillById(id);
    }

    @PostMapping("/{id}/pay")
    public VendorBill markAsPaid(
            @PathVariable Long id) {

        return vendorBillService.markAsPaid(id);
    }

    @DeleteMapping("/{id}")
    public String deleteVendorBill(
            @PathVariable Long id) {

        vendorBillService.deleteVendorBill(id);

        return "Vendor bill deleted successfully";
    }
}