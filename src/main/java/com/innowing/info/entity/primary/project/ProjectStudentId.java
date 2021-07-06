package com.innowing.info.entity.primary.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectStudentId implements Serializable {


    private Long projectId;
    private Long eligibleStudentId;

}
