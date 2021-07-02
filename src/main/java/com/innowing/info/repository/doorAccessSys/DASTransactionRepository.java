package com.innowing.info.repository.doorAccessSys;

import com.innowing.info.entity.doorAccessSys.DASTransaction;
import com.innowing.info.entity.doorAccessSys.DASUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DASTransactionRepository extends JpaRepository<DASTransaction, Integer> {
//    @Query("SELECT d from DASTransaction LEFT JOIN u.")
    List<DASTransaction> findAllByTransactionDateTimeBetween(LocalDateTime start, LocalDateTime end);
    List<DASTransaction> findAllByCardId(String cardId);
}
