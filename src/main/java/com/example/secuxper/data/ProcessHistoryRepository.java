package com.example.secuxper.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface ProcessHistoryRepository extends JpaRepository<ProcessHistory, Long> {

    Page<ProcessHistory> findAll(Pageable pageable);
    @Query("SELECT ph FROM ProcessHistory ph " +
            "WHERE (:startDate IS NULL OR ph.processDatetime >= :startDate) " +
            "AND (:endDate IS NULL OR ph.processDatetime <= :endDate) " +
            "AND (ph.name LIKE %:handle% OR :handle IS NULL) " +
            "AND (ph.accessIp LIKE %:accessIp% OR :accessIp IS NULL) " +
            "AND (:processType IS NULL OR ph.processType = :processType)")
    Page<ProcessHistory> findFilteredList(LocalDate startDate,
                                          LocalDate endDate,
                                          String handle,
                                          String accessIp,
                                          String processType,
                                          Pageable pageable);


    ProcessHistory findById(String id);
}
