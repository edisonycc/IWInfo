package com.innowing.info.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "Student")
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long hkuId;
    private String title;
    private String name;
    private String chineseName;
    private String gender;
    private String email;
    private String cardId;
    private String faculty;
    private String department;
    private String curriculum;
    private Integer academicYear;
    private Integer studyYear;
// private String memberType;
    private String academicCareer;
    private boolean isInnoHubMember;
    private String memberStatus;
}
