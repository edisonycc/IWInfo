package com.innowing.info.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EligibleStudentSearchCriteria {
    private String name;
    private Long hkuId;
    private String email;
    private Integer studyYear;
    private String faculty;
    private String department;
    private String curriculum;
    private String gender;
    private Boolean appliedMembership;
    private Boolean agreedToPay;
    private Boolean siteVisited;
    private Boolean passedQuiz;
    private Boolean sentToFeo;
    private Boolean depositPaid;
    private Boolean accessGranted;
    private Boolean confirmedMember;
    private Boolean isActive;
    private Boolean missingHkuId;

//    private String memberStatus;
}
