package com.example.usereadreplicademoaop.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author pgayal
 * created on 03/03/2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EngagementDTO {
    private Long id;
    private Long participantId;
    private Long engagementType;
    private Long engagementOutcome;
    private Date engagementDatetime;
    private String summary;
    private Integer engagementDuration;
}
