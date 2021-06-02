package com.innowing.info.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "EligibleStudent")
@Table(name = "eligible_student")
public class EligibleStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    private Long hkuId;
    private String title;
    private String name;
    private String chineseName;
    private String curriculum;
    private String gender;
    private String faculty;
    private String department;
    private String academicCareer;
    private Integer academicYear;
    private Integer courseYear;
    private Integer studyYear;
    private String email;
    private String cardId;
    // private String phoneNumber;
    // private String partTime;
    private Boolean appliedMembership;
    private Boolean agreedToPay;
    private Boolean siteVisited;
    private Boolean passedQuiz;
    private Boolean depositPaid;
    private Boolean accessGranted;
}
