package com.innowing.info.entity.primary.project;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ProjectStaffId implements Serializable {
    private Long projectId;
    private Long staffId;
}
