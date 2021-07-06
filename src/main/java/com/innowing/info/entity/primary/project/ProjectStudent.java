package com.innowing.info.entity.primary.project;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.innowing.info.entity.primary.EligibleStudent;
import lombok.AllArgsConstructor;
import lombok.Data;
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

    @ManyToOne
    @MapsId("projectId")
//    @JsonBackReference
    @JoinColumn(
            name = "project_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "project_student_project_id_fk"
            )
    )
    private Project project;

    @ManyToOne
    @MapsId("eligibleStudentId")
//    @JsonBackReference
    @JoinColumn(
            name = "eligible_student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "project_eligible_student_eligible_student_id_fk"
            )
    )
    private EligibleStudent eligibleStudent;

    private String role;


//    public ProjectStudent(Project project, EligibleStudent student, String role) {
//        this.project = project;
//        this.student = student;
//        this.role = role;
//    }


}


