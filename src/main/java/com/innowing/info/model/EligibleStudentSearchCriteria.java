package com.innowing.info.model;

import lombok.Data;

@Data
public class EligibleStudentSearchCriteria {
    private String name;
    private Long hkuId;
    private Integer studyYear;
    private String faculty;
    private String department;
    private String curriculum;
    private Boolean appliedMembership;
    private Boolean agreedToPay;
    private Boolean siteVisited;
    private Boolean passedQuiz;
    private Boolean depositPaid;
    private Boolean accessGranted;
//    private String memberStatus;
}
