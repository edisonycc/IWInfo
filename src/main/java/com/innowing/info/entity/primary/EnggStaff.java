package com.innowing.info.entity.primary;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "EnggStaff")
@Table(name = "engg_staff")
public class EnggStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long uid;
    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private String staffCategory;
    private String department;
    private String cardId;


}
