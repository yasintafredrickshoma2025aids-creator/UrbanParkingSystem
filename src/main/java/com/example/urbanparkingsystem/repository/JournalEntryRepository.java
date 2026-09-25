package com.example.urbanparkingsystem.repository;

import com.example.urbanparkingsystem.entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalEntryRepository
        extends JpaRepository<JournalEntry, Long> {
}