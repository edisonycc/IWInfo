package com.innowing.info.model;

import lombok.Data;

@Data
public class StaffSearchCriteria {
    private Long hkuId;
    private String name;
    private String email;
    private String gender;
    private String cardId;
    private String faculty;
    private String department;
    private String title;
    private String staffCategory;
    private Boolean accessGranted;
}
