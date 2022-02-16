package com.innowing.info.repository.doorAccessSys;

import com.innowing.info.entity.doorAccessSys.DASTransaction;
import com.innowing.info.entity.doorAccessSys.DASUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public interface DASTransactionRepository extends JpaRepository<DASTransaction, Integer> {
//    @Query("SELECT d from DASTransaction LEFT JOIN u.")
    List<DASTransaction> findAllByTransactionDateTimeBetween(LocalDateTime start, LocalDateTime end);
    List<DASTransaction> findAllByCardId(String cardId);

    @Query("SELECT count(dt) from DASTransaction dt where dt.cardId in ?1")
    List<DASTransaction> findByCardIdIn(List<String> cardIdList);

    //@Query("SELECT count(dt) from DASTransaction dt where dt.cardId in ?1")
    int countByCardIdInAndTransactionDateTimeBetweenAndDoorIdIn(List<String> cardIdList, LocalDateTime start, LocalDateTime end, List<Integer> doorIdList);

    @Query(value =
            // "SELECT COUNT(DISTINCT [iMS].[dbo].[tbTransaction].[CardID]) AS nums, " +
            "SELECT COUNT([iMS].[dbo].[tbTransaction].[CardID]) AS nums, " +
            "CAST([TransactionDate] AS DATE) AS date " +
                    "FROM [tbTransaction] " +
                    "WHERE [CardID] in ?1 " +
                    "AND [DoorID] in ?2 " +
                    "AND CAST([TransactionDate] AS DATE) >= ?3 " +
                    "AND CAST([TransactionDate] AS DATE) <= ?4 " +
                    "GROUP BY CAST([TransactionDate] AS DATE)",
            nativeQuery = true)
    List<Object[]> getTransactionCountByDay(List<String> cardIdList, List<Integer> doorIdList, LocalDateTime startDateTime, LocalDateTime endDateTime);

}
