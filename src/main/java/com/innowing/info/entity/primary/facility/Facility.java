package com.innowing.info.entity.primary.facility;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "Facility")
@Table(name = "facility")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String location;

    @OneToMany(
            mappedBy = "facility",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference(value = "facility-students-skill")
    private List<FacilityStudentSkill> facilityStudentSkills;

    @OneToMany(
            mappedBy = "facility",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference(value = "facility-students-usage")
    private List<FacilityStudentUsage> facilityStudentUsages;

    @OneToMany(
            mappedBy = "facility",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference(value = "facility-staffs-skill")
    private List<FacilityStaffSkill> facilityStaffSkills;

    public void setFacilityStudentSkills(List<FacilityStudentSkill> studentFacilitySkills) {
        if(this.facilityStudentSkills == null) {
            this.facilityStudentSkills = studentFacilitySkills;
        } else {
            this.facilityStudentSkills.clear();
            this.facilityStudentSkills.addAll(studentFacilitySkills);
        }
    }

    public void setFacilityStudentUsages(List<FacilityStudentUsage> facilityStudentUsages) {
        if(this.facilityStudentUsages == null) {
            this.facilityStudentUsages = facilityStudentUsages;
        } else {
            this.facilityStudentUsages.clear();
            this.facilityStudentUsages.addAll(facilityStudentUsages);
        }
    }
}
