package com.innowing.info.entity.primary.facility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacilityStudentId implements Serializable {
    private Long facilityId;
    private Long eligibleStudentId;
}
