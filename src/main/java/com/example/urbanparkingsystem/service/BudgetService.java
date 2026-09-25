package com.example.urbanparkingsystem.service;

import com.example.urbanparkingsystem.entity.Budget;
import com.example.urbanparkingsystem.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;

    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public Budget createBudget(Budget budget) {

        if (budget.getActualAmount() == null) {
            budget.setActualAmount(0.0);
        }

        return budgetRepository.save(budget);
    }

    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public Budget getBudgetById(Long id) {
        return budgetRepository.findById(id).orElse(null);
    }

    public Budget updateActualAmount(Long id, Double actualAmount) {

        Budget budget =
                budgetRepository.findById(id).orElse(null);

        if (budget == null) {
            return null;
        }

        budget.setActualAmount(actualAmount);

        return budgetRepository.save(budget);
    }

    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }
}