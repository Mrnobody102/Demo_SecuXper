package com.example.secuxper.dto;

import com.example.secuxper.data.ProcessHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessHistoryDto {
    private Long id;
    private String loginId;
    private String name;
    private String processMenu;
    private String processType;
    private String processTaskLink;
    private int userInformationNumber;
    private int privacyItemNumber;
    private String accessIp;
    private String processDatetime;


}