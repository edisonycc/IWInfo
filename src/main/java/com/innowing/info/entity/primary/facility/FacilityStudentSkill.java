package com.innowing.info.entity.primary.facility;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.innowing.info.entity.primary.EligibleStudent;
import com.innowing.info.entity.primary.project.Project;
import com.innowing.info.entity.primary.project.ProjectStudentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "facility_student_skill")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacilityStudentSkill {
    @EmbeddedId
    private FacilityStudentId id;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @MapsId("facilityId")
    @JsonBackReference(value = "facility-students-skill")
    @JoinColumn(
            name = "facility_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "facility_student_skill_facility_id_fk"
            )
    )
    @EqualsAndHashCode.Exclude
    private Facility facility;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @MapsId("eligibleStudentId")
    @JsonBackReference(value = "student-facilities-skill")
    @JoinColumn(
            name = "eligible_student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "facility_student_skill_student_id_fk"
            )
    )
    @EqualsAndHashCode.Exclude
    private EligibleStudent eligibleStudent;

    private Integer level;

    @Override
    // fix StackOverflowError
    public String toString() {
        return "facilityStudentSkills:{" +
                "id:{ eligibleStudentId:" + id.getEligibleStudentId() + "," +
                "facilityId:" + id.getFacilityId() +
                "}, " +
                "level:" + level +
                '}';
    }
}
