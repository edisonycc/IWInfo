package com.innowing.info.service.doorAccessSys;

import com.innowing.info.common.ResponseEnum;
import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.doorAccessSys.DASTransaction;
import com.innowing.info.entity.doorAccessSys.DASUser;
import com.innowing.info.repository.doorAccessSys.DASTransactionRepository;
import com.innowing.info.repository.doorAccessSys.DASUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Slf4j
public class DASTransactionService {
    private final DASTransactionRepository dasTransactionRepository;

    @Autowired
    public DASTransactionService(DASTransactionRepository dasTransactionRepository) {
        this.dasTransactionRepository = dasTransactionRepository;
    }

    public ServerResponse getDASTransactionByDate(int minusDays) {
        LocalDate today = LocalDate.now();
        LocalDate selectedDay = today.minusDays(minusDays);
        LocalDateTime startOfDay = LocalDateTime.of(selectedDay, LocalTime.MIDNIGHT);
        LocalDateTime endOfDay = LocalDateTime.of(selectedDay, LocalTime.MAX);
        List<DASTransaction> transactions = dasTransactionRepository.findAllByTransactionDateTimeBetween(startOfDay, endOfDay);
        return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data("transactions", transactions);
    }


    public ServerResponse getDASTransactionByCardId(String cardId) {
        try {
            List<DASTransaction> transactions = dasTransactionRepository.findAllByCardId(cardId);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data("transactions", transactions);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return ServerResponse.getInstance().responseEnum(ResponseEnum.FAILED);
    }
}
