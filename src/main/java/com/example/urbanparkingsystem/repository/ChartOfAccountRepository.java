package com.example.urbanparkingsystem.repository;

import com.example.urbanparkingsystem.entity.ChartOfAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChartOfAccountRepository
        extends JpaRepository<ChartOfAccount, Long> {
}