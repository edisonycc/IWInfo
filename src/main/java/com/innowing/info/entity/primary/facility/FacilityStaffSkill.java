package com.innowing.info.entity.primary.facility;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.innowing.info.entity.primary.Staff;
import com.innowing.info.entity.primary.project.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "facility_staff_skill")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacilityStaffSkill {
    @EmbeddedId
    private FacilityStaffId id;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @MapsId("facilityId")
    @JsonBackReference(value = "facility-staffs-skill")
    @JoinColumn(
            name = "facility_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "facility_staff_skill_facility_id_fk"
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
    @MapsId("staffId")
    @JsonBackReference(value = "staff-facilities-skill")
    @JoinColumn(
            name = "staff_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "facility_staff_skill_staff_id_fk"
            )
    )
    @EqualsAndHashCode.Exclude
    private Staff staff;

    private Integer level;

    @Override
    // fix StackOverflowError
    public String toString() {
        return "facilityStaffSkills:{" +
                "id:{ staffId:" + id.getStaffId() + "," +
                "facilityId:" + id.getFacilityId() +
                "}, " +
                "level:" + level +
                '}';
    }
}
