package com.example.urbanparkingsystem.controller;

import com.example.urbanparkingsystem.entity.ChartOfAccount;
import com.example.urbanparkingsystem.service.ChartOfAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chart-of-accounts")
public class ChartOfAccountController {

    private final ChartOfAccountService chartOfAccountService;

    public ChartOfAccountController(
            ChartOfAccountService chartOfAccountService) {

        this.chartOfAccountService = chartOfAccountService;
    }

    @PostMapping
    public ChartOfAccount addAccount(
            @RequestBody ChartOfAccount account) {

        return chartOfAccountService.addAccount(account);
    }

    @GetMapping
    public List<ChartOfAccount> getAllAccounts() {

        return chartOfAccountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public ChartOfAccount getAccountById(
            @PathVariable Long id) {

        return chartOfAccountService.getAccountById(id);
    }

    @PutMapping("/{id}/balance")
    public ChartOfAccount updateBalance(
            @PathVariable Long id,
            @RequestParam Double balance) {

        return chartOfAccountService.updateBalance(id, balance);
    }

    @DeleteMapping("/{id}")
    public String deleteAccount(
            @PathVariable Long id) {

        chartOfAccountService.deleteAccount(id);

        return "Account deleted successfully";
    }
}