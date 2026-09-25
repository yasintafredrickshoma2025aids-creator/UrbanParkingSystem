package com.example.urbanparkingsystem.service;

import com.example.urbanparkingsystem.entity.ChartOfAccount;
import com.example.urbanparkingsystem.repository.ChartOfAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final ChartOfAccountRepository chartOfAccountRepository;

    public ReportService(ChartOfAccountRepository chartOfAccountRepository) {
        this.chartOfAccountRepository = chartOfAccountRepository;
    }

    public double calculateTotalIncome() {

        List<ChartOfAccount> accounts =
                chartOfAccountRepository.findAll();

        double totalIncome = 0.0;

        for (ChartOfAccount account : accounts) {

            if ("INCOME".equals(account.getAccountType())
                    && account.getBalance() != null) {

                totalIncome += account.getBalance();
            }
        }

        return totalIncome;
    }

    public double calculateTotalExpenses() {

        List<ChartOfAccount> accounts =
                chartOfAccountRepository.findAll();

        double totalExpenses = 0.0;

        for (ChartOfAccount account : accounts) {

            if ("EXPENSE".equals(account.getAccountType())
                    && account.getBalance() != null) {

                totalExpenses += account.getBalance();
            }
        }

        return totalExpenses;
    }

    public double calculateProfitOrLoss() {

        double income = calculateTotalIncome();
        double expenses = calculateTotalExpenses();

        return income - expenses;
    }
}