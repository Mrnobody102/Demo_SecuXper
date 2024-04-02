package com.example.secuxper.service;

import com.example.secuxper.data.ProcessHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ProcessHistoryService {

    public Page<ProcessHistory> getAllProcessHistories(Pageable pageable);
    public Page<ProcessHistory> getFilteredProcessHistories(LocalDate startDate, LocalDate endDate, String handle, String accessIp, String processType, Pageable pageable);
    ProcessHistory saveProcessHistory(ProcessHistory processHistory);

    public ProcessHistory getProcessHistoryById(String id);
}
