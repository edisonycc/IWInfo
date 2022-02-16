package com.innowing.info.entity.doorAccessSys;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity(name = "DASTransaction")
@Table(name = "tbTransaction")
public class DASTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransactionId")
    private Integer id;
    @Column(name = "TransactionDate")
    private LocalDateTime transactionDateTime;
    @Column(name = "TransactionTypeID")
    private Integer transactionTypeID; // 1 => in; 2 => out
    @Column(name = "CardId")
    private String cardId;
    @Column(name = "DoorId")
    private Integer doorId;
    @Column(name = "UserId", updatable=false, insertable = false)
    private Integer dasUserId;
//    @Transient
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "UserId",updatable = false, insertable = false)
    private DASUser dasUser;
//    @Column(name = "UserId")
//    private Integer userId;

}
