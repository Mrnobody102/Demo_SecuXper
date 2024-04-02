package com.example.secuxper.controller;


import com.example.secuxper.data.ProcessHistory;
import com.example.secuxper.service.ProcessHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/setting")
@CrossOrigin
@RequiredArgsConstructor
public class ProcessHistoryController {
    private final ProcessHistoryService processHistoryService;

    @PostMapping("/process_history/add")
    public String add(@RequestBody ProcessHistory processHistory) {
        processHistoryService.saveProcessHistory(processHistory);
        return "Saved process!";
    }

    @GetMapping("/process_history_list")
    public Page<ProcessHistory> getProcessHistories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return processHistoryService.getAllProcessHistories(pageRequest);
    }

    @GetMapping("/process_history/filter")
    public Page<ProcessHistory> getFilteredProcessHistories(
            @RequestParam(defaultValue = "") String startDate,
            @RequestParam(defaultValue = "") String endDate,
            @RequestParam(defaultValue = "") String handle,
            @RequestParam(defaultValue = "") String accessIp,
            @RequestParam(defaultValue = "Setting") String processType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate convertedStartDate = startDate.equals("") ? null : LocalDate.parse(startDate, formatter);
        LocalDate convertedEndDate = endDate.equals("")? null : LocalDate.parse(endDate, formatter);
        return processHistoryService.getFilteredProcessHistories(convertedStartDate, convertedEndDate, handle, accessIp, processType, pageRequest);
    }

    @GetMapping("/process_history/{id}")
    @ResponseBody
    public ProcessHistory getProcessHistories(
            @PathVariable("id") String id) {
        return processHistoryService.getProcessHistoryById(id);
    }



}
