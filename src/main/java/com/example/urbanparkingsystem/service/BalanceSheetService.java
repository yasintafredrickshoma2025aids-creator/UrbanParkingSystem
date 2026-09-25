package com.example.urbanparkingsystem.service;

import com.example.urbanparkingsystem.entity.ChartOfAccount;
import com.example.urbanparkingsystem.repository.ChartOfAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceSheetService {

    private final ChartOfAccountRepository chartOfAccountRepository;

    public BalanceSheetService(
            ChartOfAccountRepository chartOfAccountRepository) {

        this.chartOfAccountRepository = chartOfAccountRepository;
    }

    public double calculateTotalAssets() {

        List<ChartOfAccount> accounts =
                chartOfAccountRepository.findAll();

        double totalAssets = 0.0;

        for (ChartOfAccount account : accounts) {

            if ("ASSET".equals(account.getAccountType())
                    && account.getBalance() != null) {

                totalAssets += account.getBalance();
            }
        }

        return totalAssets;
    }

    public double calculateTotalLiabilities() {

        List<ChartOfAccount> accounts =
                chartOfAccountRepository.findAll();

        double totalLiabilities = 0.0;

        for (ChartOfAccount account : accounts) {

            if ("LIABILITY".equals(account.getAccountType())
                    && account.getBalance() != null) {

                totalLiabilities += account.getBalance();
            }
        }

        return totalLiabilities;
    }

    public double calculateNetPosition() {

        double assets = calculateTotalAssets();
        double liabilities = calculateTotalLiabilities();

        return assets - liabilities;
    }
}