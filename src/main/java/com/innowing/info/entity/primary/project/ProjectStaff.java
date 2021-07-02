package com.innowing.info.entity.primary.project;

import com.innowing.info.entity.primary.Staff;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "project_staff")
@Data
public class ProjectStaff {

    @EmbeddedId
    private ProjectStaffId id;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "project_staff_project_id_fk"
            )
    )
    private Project project;

    @ManyToOne
    @MapsId("staffId")
    @JoinColumn(
            name = "staff_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "project_staff_staff_id_fk"
            ))
    private Staff staff;

    private String role;

}
