package com.innowing.info.entity.primary.project;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.innowing.info.entity.primary.EligibleStudent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "project_student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectStudent {

    @EmbeddedId
    private ProjectStudentId id;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @MapsId("projectId")
    @JsonBackReference(value = "project-students")
    @JoinColumn(
            name = "project_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "project_student_project_id_fk"
            )
    )
    @EqualsAndHashCode.Exclude
    private Project project;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @MapsId("eligibleStudentId")
//    @JsonIgnore
    @JsonBackReference(value = "student-projects")
    @JoinColumn(
            name = "eligible_student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "project_eligible_student_eligible_student_id_fk"
            )
    )
    @EqualsAndHashCode.Exclude
    private EligibleStudent eligibleStudent;

    private String role;

    @Override
    public String toString() {
        return "projectStudents:{" +
                "id:{ eligibleStudentId:" + id.getEligibleStudentId() + "," +
                    "projectId:" + id.getProjectId() +
                "}, " +
                "role:" + role +
                '}';
    }





}


