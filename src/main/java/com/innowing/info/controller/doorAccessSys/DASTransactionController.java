package com.innowing.info.controller.doorAccessSys;

import com.innowing.info.common.ResponseEnum;
import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.doorAccessSys.DASTransaction;
import com.innowing.info.repository.doorAccessSys.DASTransactionRepository;
import com.innowing.info.service.doorAccessSys.DASTransactionService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "transactions")
public class DASTransactionController {

    private final DASTransactionService dasTransactionService;

    @Autowired
    DASTransactionRepository dasTransactionRepository;

    @Autowired
    public DASTransactionController(DASTransactionService dasTransactionService) {
        this.dasTransactionService = dasTransactionService;
    }

    @GetMapping("/days")
    @ResponseBody
    public ServerResponse getDASTransactionByDate(@RequestParam int minusDays) {
        return dasTransactionService.getDASTransactionByDate(minusDays);
    }
    @GetMapping("/card-id")
    @ResponseBody
    public ServerResponse getDASTransactionByCardId(@RequestParam String cardId) {
        return dasTransactionService.getDASTransactionByCardId(cardId);
    }

    @GetMapping("/testsql")
    @ResponseBody
    public ServerResponse getTestSql() {
        List<String> cardIdList = Arrays.asList("805D3872796104");//"805D38723F0504",
        // 13 -> LG07 Digital learning studio
        List<Integer> doorIdList = Arrays.asList(13);
        LocalDateTime startDateTime = LocalDateTime.of(2022, Month.JANUARY, 24, 0, 0, 0);
        LocalDateTime endDateTime = LocalDateTime.now();
//        String startDateTime = "2022-01-01";
//        String endDateTime = "2022-01-10";


        //List<DASTransaction> testList = dasTransactionRepository.findByCardIdIn(cardIdList);
        //int testCount = dasTransactionRepository.countByCardIdInAndTransactionDateTimeBetween(cardIdList, startDateTime, endDateTime);
        //int testCount = dasTransactionRepository.countByCardIdInAndTransactionDateTimeBetweenAndDoorIdIn(cardIdList, startDateTime, endDateTime,doorIdList);
        List<Object[]> testCount = dasTransactionRepository.getTransactionCountByDay(cardIdList, doorIdList, startDateTime, endDateTime);
        return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data("transactions", testCount);
    }

    @GetMapping("/attendance")
    @ResponseBody
    public ServerResponse getDASTransactionCount(@RequestParam long projectId,
                                                 @RequestParam(value="doorIdList") List<String> doorIdList,
                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime endDateTime) {
        return dasTransactionService.getDASTransactionCount(projectId, doorIdList,startDateTime, endDateTime);
    }

}
