package com.example.urbanparkingsystem.service;

import com.example.urbanparkingsystem.entity.ChartOfAccount;
import com.example.urbanparkingsystem.entity.JournalEntry;
import com.example.urbanparkingsystem.entity.ParkingInvoice;
import com.example.urbanparkingsystem.repository.ChartOfAccountRepository;
import com.example.urbanparkingsystem.repository.JournalEntryRepository;
import com.example.urbanparkingsystem.repository.ParkingInvoiceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingInvoiceService {

    private final ParkingInvoiceRepository parkingInvoiceRepository;
    private final JournalEntryRepository journalEntryRepository;
    private final ChartOfAccountRepository chartOfAccountRepository;

    public ParkingInvoiceService(
            ParkingInvoiceRepository parkingInvoiceRepository,
            JournalEntryRepository journalEntryRepository,
            ChartOfAccountRepository chartOfAccountRepository) {

        this.parkingInvoiceRepository = parkingInvoiceRepository;
        this.journalEntryRepository = journalEntryRepository;
        this.chartOfAccountRepository = chartOfAccountRepository;
    }

    public ParkingInvoice createInvoice(ParkingInvoice invoice) {

        if (invoice.getInvoiceDate() == null) {
            invoice.setInvoiceDate(LocalDateTime.now());
        }

        if (invoice.getPaymentStatus() == null) {
            invoice.setPaymentStatus("PENDING");
        }

        return parkingInvoiceRepository.save(invoice);
    }

    public List<ParkingInvoice> getAllInvoices() {
        return parkingInvoiceRepository.findAll();
    }

    public ParkingInvoice getInvoiceById(Long id) {
        return parkingInvoiceRepository.findById(id).orElse(null);
    }

    public ParkingInvoice markAsPaid(Long id) {

        ParkingInvoice invoice =
                parkingInvoiceRepository.findById(id).orElse(null);

        if (invoice == null) {
            return null;
        }

        invoice.setPaymentStatus("PAID");

        ParkingInvoice savedInvoice =
                parkingInvoiceRepository.save(invoice);

        ChartOfAccount revenueAccount =
                chartOfAccountRepository.findById(1L).orElse(null);

        if (revenueAccount != null) {

            double currentBalance =
                    revenueAccount.getBalance() == null
                            ? 0.0
                            : revenueAccount.getBalance();

            double amount =
                    invoice.getAmount() == null
                            ? 0.0
                            : invoice.getAmount();

            revenueAccount.setBalance(currentBalance + amount);

            chartOfAccountRepository.save(revenueAccount);

            JournalEntry journalEntry = new JournalEntry();

            journalEntry.setAccountId(1L);
            journalEntry.setEntryType("CREDIT");
            journalEntry.setAmount(amount);
            journalEntry.setDescription(
                    "Parking payment received for invoice "
                            + invoice.getId());
            journalEntry.setEntryDate(LocalDate.now());

            journalEntryRepository.save(journalEntry);
        }

        return savedInvoice;
    }
}