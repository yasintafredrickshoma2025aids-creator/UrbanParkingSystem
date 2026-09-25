package com.example.urbanparkingsystem.controller;

import com.example.urbanparkingsystem.entity.JournalEntry;
import com.example.urbanparkingsystem.service.JournalEntryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal-entries")
public class JournalEntryController {

    private final JournalEntryService journalEntryService;

    public JournalEntryController(
            JournalEntryService journalEntryService) {

        this.journalEntryService = journalEntryService;
    }

    @PostMapping
    public JournalEntry createJournalEntry(
            @RequestBody JournalEntry journalEntry) {

        return journalEntryService.createJournalEntry(journalEntry);
    }

    @GetMapping
    public List<JournalEntry> getAllJournalEntries() {

        return journalEntryService.getAllJournalEntries();
    }

    @GetMapping("/{id}")
    public JournalEntry getJournalEntryById(
            @PathVariable Long id) {

        return journalEntryService.getJournalEntryById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteJournalEntry(
            @PathVariable Long id) {

        journalEntryService.deleteJournalEntry(id);

        return "Journal entry deleted successfully";
    }
}