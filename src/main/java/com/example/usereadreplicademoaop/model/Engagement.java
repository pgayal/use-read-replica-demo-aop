package com.example.usereadreplicademoaop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author pgayal
 * created on 03/03/2022
 */
@Entity
@Table(name = "engagement")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Engagement {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "participant_id")
    private Long participantId;

    @Column(name = "engagement_outcome_id")
    private Long engagementOutcome;

    @Column(name = "engagement_type_id")
    private Long engagementType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "engagement_datetime")
    private Date engagementDatetime;

    @Column(name = "engagement_duration_id")
    private Integer engagementDuration;

    @Column(name = "summary", nullable = false)
    private String summary;

}

