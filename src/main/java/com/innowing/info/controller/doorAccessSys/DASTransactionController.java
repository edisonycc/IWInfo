package com.innowing.info.controller.doorAccessSys;

import com.innowing.info.common.ServerResponse;
import com.innowing.info.service.doorAccessSys.DASTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "transactions")
public class DASTransactionController {

    private final DASTransactionService dasTransactionService;

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
}
