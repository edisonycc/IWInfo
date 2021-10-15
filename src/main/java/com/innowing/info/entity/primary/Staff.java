package com.innowing.info.entity.primary;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.innowing.info.entity.primary.facility.FacilityStaffSkill;
import com.innowing.info.entity.primary.facility.FacilityStudentSkill;
import com.innowing.info.entity.primary.facility.FacilityStudentUsage;
import com.innowing.info.entity.primary.project.ProjectStaff;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


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
    private String role;
    private String staffCategory;
    private Boolean accessGranted;
    private String remark;

    @OneToMany(
            mappedBy = "staff",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY  // fix InvalidDataAccessApiUsageException: Multiple representations of the same entity
    )
    @JsonManagedReference(value = "staff-projects")
    private List<ProjectStaff> staffProjects;

//    @OneToMany(
//            mappedBy = "buddyStaff",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true,
//            fetch = FetchType.LAZY
//    )
//    @Fetch(value = FetchMode.SUBSELECT)
//    @JsonManagedReference(value = "student-facility-usage-staff")
//    private List<FacilityStaffUsage> facilityStaffUsages;

    @OneToMany(
            mappedBy = "staff",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference(value = "staff-facilities-skill")
    private List<FacilityStaffSkill> facilityStaffSkills;

    public void setFacilityStaffSkills(List<FacilityStaffSkill> staffFacilitySkill) {
        if(this.facilityStaffSkills == null) {
            this.facilityStaffSkills = staffFacilitySkill;
        } else {
            this.facilityStaffSkills.clear();
            this.facilityStaffSkills.addAll(staffFacilitySkill);
        }
    }

}

