package com.example.urbanparkingsystem.controller;

import com.example.urbanparkingsystem.entity.Budget;
import com.example.urbanparkingsystem.repository.BudgetRepository;
import com.example.urbanparkingsystem.service.BalanceSheetService;
import com.example.urbanparkingsystem.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReportController {

    private final BudgetRepository budgetRepository;
    private final ReportService reportService;
    private final BalanceSheetService balanceSheetService;

    public ReportController(
            BudgetRepository budgetRepository,
            ReportService reportService,
            BalanceSheetService balanceSheetService) {

        this.budgetRepository = budgetRepository;
        this.reportService = reportService;
        this.balanceSheetService = balanceSheetService;
    }

    @GetMapping("/reports/budget")
    public List<Budget> getBudgetReport() {
        return budgetRepository.findAll();
    }

    @GetMapping("/reports/profit-loss")
    public Map<String, Double> getProfitAndLossReport() {

        double totalIncome =
                reportService.calculateTotalIncome();

        double totalExpenses =
                reportService.calculateTotalExpenses();

        double profitOrLoss =
                reportService.calculateProfitOrLoss();

        Map<String, Double> report = new HashMap<>();

        report.put("totalIncome", totalIncome);
        report.put("totalExpenses", totalExpenses);
        report.put("profitOrLoss", profitOrLoss);

        return report;
    }

    @GetMapping("/reports/balance-sheet")
    public Map<String, Double> getBalanceSheetReport() {

        double totalAssets =
                balanceSheetService.calculateTotalAssets();

        double totalLiabilities =
                balanceSheetService.calculateTotalLiabilities();

        double netPosition =
                balanceSheetService.calculateNetPosition();

        Map<String, Double> report = new HashMap<>();

        report.put("totalAssets", totalAssets);
        report.put("totalLiabilities", totalLiabilities);
        report.put("netPosition", netPosition);

        return report;
    }
}