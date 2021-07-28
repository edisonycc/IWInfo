package com.innowing.info.entity.primary.project;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.innowing.info.entity.primary.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "project_staff")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectStaff {

    @EmbeddedId
    private ProjectStaffId id;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @MapsId("projectId")
    @JsonBackReference(value = "project-staffs")
    @JoinColumn(name = "project_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "project_staff_project_id_fk"
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
    @MapsId("staffId")
    @JsonBackReference(value = "staff-projects")
    @JoinColumn(
            name = "staff_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "project_staff_staff_id_fk"
            ))
    @EqualsAndHashCode.Exclude
    private Staff staff;

    private String role;

    @Override
    public String toString() {
        return "projectStaffs:{" +
                "id:{ staffId:" + id.getStaffId() + "," +
                    "projectId:" + id.getProjectId() +
                "}, " +
                "role:" + role +
                '}';
    }

}
