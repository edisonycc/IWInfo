package com.innowing.info.entity.doorAccessSys;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity(name = "DASUserGroup")
@Table(name = "tbUserGroup")
public class DASUserGroup {
//    @Embeddable
//    public static class Pk implements Serializable {
//        @Column(name = "UserID", updatable=false)
//        private Integer dasUserId;
//        @Column(name = "GroupID", updatable=false)
//        private Integer dasGroupId;
//    }
//    @EmbeddedId
//    private Pk pk;
    @Id
    @Column(name = "UserID", updatable=false)
    private Integer dasUserId;
    @Column(name = "GroupID", updatable=false)
    private Integer dasGroupId;


//    @ManyToOne
//    @NotFound(action = NotFoundAction.IGNORE)
//    @JoinColumn(name = "UserId",updatable = false)
//    private Integer dasGroupId;
}
