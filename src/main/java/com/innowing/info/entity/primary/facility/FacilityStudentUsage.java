package com.innowing.info.entity.primary.facility;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.innowing.info.entity.primary.EligibleStudent;
import com.innowing.info.entity.primary.Staff;
import com.innowing.info.entity.primary.project.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "facility_student_usage")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacilityStudentUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(
            name = "facility_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "facility_student_usage_facility_id_fk"
            )
    )
    @JsonBackReference(value = "facility-students-usage")
    private Facility facility;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(
            name = "eligible_student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "facility_student_usage_student_id_fk"
            )
    )
    @JsonBackReference(value = "student-facilities-usage")
    private EligibleStudent eligibleStudent;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(
            name = "project_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "facility_student_usage_project_id_fk"
            )
    )
    @JsonBackReference(value = "student-facility-usage-project")
    private Project project;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(
            name = "buddy_staff_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "facility_student_usage_staff_id_fk"
            )
    )
    @JsonBackReference(value = "student-facility-usage-staff")
    private Staff buddyStaff;

}
