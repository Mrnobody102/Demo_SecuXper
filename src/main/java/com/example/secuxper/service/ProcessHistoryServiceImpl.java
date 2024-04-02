package com.example.secuxper.service;

import com.example.secuxper.data.ProcessHistory;
import com.example.secuxper.data.ProcessHistoryRepository;
import com.example.secuxper.dto.ProcessHistoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ProcessHistoryServiceImpl implements ProcessHistoryService{
    private final ProcessHistoryRepository processHistoryRepository;

    public ProcessHistoryServiceImpl(ProcessHistoryRepository processHistoryRepository) {
        this.processHistoryRepository = processHistoryRepository;
    }

    @Override
    public Page<ProcessHistory> getAllProcessHistories( Pageable pageable) {
        Page<ProcessHistory> processHistories = processHistoryRepository.findAll(pageable);
        return processHistories;
//                .map(ProcessHistoryDto::fromEntity);
    }
    @Override
    public Page<ProcessHistory> getFilteredProcessHistories(LocalDate startDate, LocalDate endDate, String handle, String accessIp, String processType, Pageable pageable) {
        Page<ProcessHistory> processHistories = processHistoryRepository.findFilteredList(startDate, endDate, handle, accessIp, processType, pageable);
        return processHistories;
//                .map(ProcessHistoryDto::fromEntity);
    }

    @Override
    public ProcessHistory saveProcessHistory(ProcessHistory processHistory) {
        return processHistoryRepository.save(processHistory);
    }

    @Override
    public ProcessHistory getProcessHistoryById(String id) {
        return processHistoryRepository.findById(id);
    }
}
