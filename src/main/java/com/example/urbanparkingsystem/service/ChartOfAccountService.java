package com.example.urbanparkingsystem.service;

import com.example.urbanparkingsystem.entity.ChartOfAccount;
import com.example.urbanparkingsystem.repository.ChartOfAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartOfAccountService {

    private final ChartOfAccountRepository chartOfAccountRepository;

    public ChartOfAccountService(
            ChartOfAccountRepository chartOfAccountRepository) {

        this.chartOfAccountRepository = chartOfAccountRepository;
    }

    public ChartOfAccount addAccount(ChartOfAccount account) {

        if (account.getBalance() == null) {
            account.setBalance(0.0);
        }

        return chartOfAccountRepository.save(account);
    }

    public List<ChartOfAccount> getAllAccounts() {
        return chartOfAccountRepository.findAll();
    }

    public ChartOfAccount getAccountById(Long id) {
        return chartOfAccountRepository.findById(id).orElse(null);
    }

    public ChartOfAccount updateBalance(Long id, Double balance) {

        ChartOfAccount account =
                chartOfAccountRepository.findById(id).orElse(null);

        if (account == null) {
            return null;
        }

        account.setBalance(balance);

        return chartOfAccountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        chartOfAccountRepository.deleteById(id);
    }
}