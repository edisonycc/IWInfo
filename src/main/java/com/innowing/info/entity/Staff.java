package com.innowing.info.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "Staff")
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long hkuId;
    private String name;
    private String email;
    private String gender;
    private Long cardId;
    private String faculty;
    private String department;
    private String programName;
    private String title;
    private String staffCategory;


    public Staff(Long hkuId, String name, String email, String gender, Long cardId, String faculty, String department, String staffCategory, String title) {
        this.hkuId = hkuId;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.cardId = cardId;
        this.faculty = faculty;
        this.department = department;
        this.staffCategory = staffCategory;
        this.title = title;
    }
}

