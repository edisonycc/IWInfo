package com.innowing.info.entity.primary;

import com.innowing.info.entity.primary.project.ProjectStaff;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

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
    private String cardId;
    private String faculty;
    private String department;
    private String title;
    private String staffCategory;
    private Boolean accessGranted;

    @OneToMany(
            mappedBy = "staff",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<ProjectStaff> staffProjects;

}

