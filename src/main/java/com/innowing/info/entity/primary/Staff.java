package com.innowing.info.entity.primary;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.innowing.info.entity.primary.project.ProjectStaff;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "Staff")
@Table(name = "staff")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long hkuId;
    private String name;
    private String email;
    private String gender;
    private String cardId;
    private String faculty;
    private String department;
    private String title;
    private String staffCategory;
    private Boolean accessGranted;

    @OneToMany(
            mappedBy = "staff",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY  // fix InvalidDataAccessApiUsageException: Multiple representations of the same entity
    )
    @JsonManagedReference(value = "staff-projects")
    private List<ProjectStaff> staffProjects;

}

