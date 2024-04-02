package com.example.secuxper.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "process_histories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String loginId;
    @Column
    private String name;
    @Column
    private String processMenu;
    @Column
    private String processType;
    @Column
    private String processTaskLink;
    @Column
    private int userInformationNumber;
    @Column
    private int privacyItemNumber;
    @Column
    private String accessIp;
    @Column
    private LocalDate processDatetime;
//    @Column
//    private String employeeNumber;
//    @Column
//    private String department;
//    @Column
//    private String company;

}
