package com.example.urbanparkingsystem.service;

import com.example.urbanparkingsystem.entity.ChartOfAccount;
import com.example.urbanparkingsystem.entity.JournalEntry;
import com.example.urbanparkingsystem.repository.ChartOfAccountRepository;
import com.example.urbanparkingsystem.repository.JournalEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;
    private final ChartOfAccountRepository chartOfAccountRepository;

    public JournalEntryService(
            JournalEntryRepository journalEntryRepository,
            ChartOfAccountRepository chartOfAccountRepository) {

        this.journalEntryRepository = journalEntryRepository;
        this.chartOfAccountRepository = chartOfAccountRepository;
    }

    public JournalEntry createJournalEntry(JournalEntry journalEntry) {

        if (journalEntry.getEntryDate() == null) {
            journalEntry.setEntryDate(LocalDate.now());
        }

        ChartOfAccount account =
                chartOfAccountRepository
                        .findById(journalEntry.getAccountId())
                        .orElse(null);

        if (account != null) {

            double currentBalance = account.getBalance() == null
                    ? 0.0
                    : account.getBalance();

            double amount = journalEntry.getAmount() == null
                    ? 0.0
                    : journalEntry.getAmount();

            if ("DEBIT".equalsIgnoreCase(journalEntry.getEntryType())) {
                account.setBalance(currentBalance + amount);
            }

            if ("CREDIT".equalsIgnoreCase(journalEntry.getEntryType())) {
                account.setBalance(currentBalance + amount);
            }

            chartOfAccountRepository.save(account);
        }

        return journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAllJournalEntries() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry getJournalEntryById(Long id) {
        return journalEntryRepository.findById(id).orElse(null);
    }

    public void deleteJournalEntry(Long id) {
        journalEntryRepository.deleteById(id);
    }
}