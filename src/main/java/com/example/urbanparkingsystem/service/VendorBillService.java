package com.example.urbanparkingsystem.service;

import com.example.urbanparkingsystem.entity.VendorBill;
import com.example.urbanparkingsystem.repository.VendorBillRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VendorBillService {

    private final VendorBillRepository vendorBillRepository;

    public VendorBillService(VendorBillRepository vendorBillRepository) {
        this.vendorBillRepository = vendorBillRepository;
    }

    public VendorBill createVendorBill(VendorBill vendorBill) {

        if (vendorBill.getBillDate() == null) {
            vendorBill.setBillDate(LocalDate.now());
        }

        if (vendorBill.getPaymentStatus() == null) {
            vendorBill.setPaymentStatus("PENDING");
        }

        return vendorBillRepository.save(vendorBill);
    }

    public List<VendorBill> getAllVendorBills() {
        return vendorBillRepository.findAll();
    }

    public VendorBill getVendorBillById(Long id) {
        return vendorBillRepository.findById(id).orElse(null);
    }

    public VendorBill markAsPaid(Long id) {

        VendorBill vendorBill =
                vendorBillRepository.findById(id).orElse(null);

        if (vendorBill == null) {
            return null;
        }

        vendorBill.setPaymentStatus("PAID");

        return vendorBillRepository.save(vendorBill);
    }

    public void deleteVendorBill(Long id) {
        vendorBillRepository.deleteById(id);
    }
}