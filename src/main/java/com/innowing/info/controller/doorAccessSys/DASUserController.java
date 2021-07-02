package com.innowing.info.controller.doorAccessSys;

import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.doorAccessSys.DASUser;
import com.innowing.info.service.doorAccessSys.DASUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "dasUsers")
public class DASUserController {
    @Autowired
    DASUserService dasUserService;

    @GetMapping
    public ServerResponse getDASUsers() {
        return dasUserService.getDasUsers();
    }

    @GetMapping("{hkuId}")
    public ServerResponse getDASUserByHkuId(@PathVariable long hkuId) {
        return dasUserService.getDASUserByHkuId(hkuId);
    }

//    @GetMapping("{id}")
//    public ServerResponse getDASUserByDASUserId(@PathVariable int id) {
//        return dasUserService.getDASUserByDASUserId(id);
//    }
}
