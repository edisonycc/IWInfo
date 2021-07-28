package com.innowing.info.entity.primary;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.innowing.info.entity.primary.project.ProjectStudent;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "EligibleStudent")
@Table(name = "eligible_student")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id") // fix Jackson infinite recursion problem
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
//    private Integer courseYear;
    private Integer studyYear;
    @Column(unique = true)
    private String email;
    private String cardId;
    // private String phoneNumber;
    // private String partTime;
    private LocalDateTime appliedMembership;
    private LocalDateTime agreedToPay;
    private LocalDateTime siteVisited;
    private LocalDateTime passedQuiz;
    private LocalDateTime sentToFeo;
    private LocalDateTime depositPaid;
    private LocalDateTime confirmedMember;
    private Boolean accessGranted;
    private Boolean isActive;
    private String remark;

    @OneToMany(
            mappedBy = "eligibleStudent",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference(value = "student-projects")
    private List<ProjectStudent> studentProjects;


//    // not working
//    @OneToOne
//    @JoinColumn(name = "hkuId", referencedColumnName = "hkuId")
//    private DASUser dasUser;
}
