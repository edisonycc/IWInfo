package com.innowing.info.entity.primary.project;

import com.fasterxml.jackson.annotation.*;
import com.innowing.info.entity.primary.facility.FacilityStudentUsage;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "Project")
@Table(name = "project")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.LAZY
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "project_category_fk"
            ))
    @ToString.Exclude
    private ProjectCategory category;
    @Column(unique = true)
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
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference(value = "project-students")
    private List<ProjectStudent> projectStudents;

    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonManagedReference(value = "project-staffs")
    private List<ProjectStaff> projectStaffs;

    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonManagedReference(value = "student-facility-usage-project")
    private List<FacilityStudentUsage> facilityStudentUsages;

    public void setProjectStudents(List<ProjectStudent> projectStudents) {
        if(this.projectStudents == null) {
            this.projectStudents = projectStudents;
        } else {
            this.projectStudents.clear();
            this.projectStudents.addAll(projectStudents);
        }
    }

    public void setProjectStaffs(List<ProjectStaff> projectStaffs) {
        if(this.projectStaffs == null) {
            this.projectStaffs = projectStaffs;
        } else {
            this.projectStaffs.clear();
            this.projectStaffs.addAll(projectStaffs);
        }
    }
}
