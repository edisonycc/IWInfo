package com.innowing.info.model;

import lombok.Data;

@Data
public class StudentSearchCriteria {
    private String name;
    private Long hkuId;
    private Integer studyYear;
    private String faculty;
    private String department;
    private String curriculum;
    private String memberStatus;

    // acem/decen

}
