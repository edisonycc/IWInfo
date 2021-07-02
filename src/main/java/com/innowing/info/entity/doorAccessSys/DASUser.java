package com.innowing.info.entity.doorAccessSys;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "DASUser")
@Table(name = "tbUser")
public class DASUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Column(name = "StaffCode")
    private String hkuId;
    @Column(name = "GenderID")
    private Integer genderId;
    @Column(name = "CardID")
    private String cardId;
//        @Column(name = "LastModifiedDate")
//        private Date lastModifiedDate;
//    @Transient
//    @OneToMany(fetch = FetchType.EAGER,
//            mappedBy = "dasUserId")
//    @Fetch(value = FetchMode.SUBSELECT)
//    private List<DASTransaction> transactionsList;
    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "dasUserId")
    private List<DASUserGroup> dasUserGroupList;
}
