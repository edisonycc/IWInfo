package com.innowing.info.entity.primary.project;

import com.innowing.info.entity.primary.EligibleStudent;
import com.innowing.info.entity.primary.Staff;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "Project")
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private String title;
    private String techTheme;
    private String hostDept;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String summary;
    private String innoUniqueness;

    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<ProjectStudent> projectStudents;
    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ProjectStaff> projectStaffs;


}
