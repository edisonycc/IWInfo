package com.innowing.info.controller;

import com.alibaba.fastjson.JSONObject;
import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.EnggStaff;
import com.innowing.info.service.EnggStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ Author     ：Edison
 * @ Date       ：Created in 12:13 2021/4/30
 * @ Description：Engg Staff Controller
 * @ Modified By：
 * @Version: 1.0
 */
@Controller
@RequestMapping(path = "api/enggStaff")
public class EnggStaffContorller {
    private final EnggStaffService enggStaffService;
    @Autowired
    public EnggStaffContorller(EnggStaffService enggStaffService) {
        this.enggStaffService = enggStaffService;
    }
    // add Engg staff
    @PostMapping(value = "add")
    @ResponseBody// 加上就能将代码return的值作为http请求的内容发回客户端，前台可以接受后台发送的json数据，不然http请求的内容默认是一个界面，会返回404
    public ServerResponse addEnggStaff(@RequestBody EnggStaff enggStaff) {
        return enggStaffService.addEnggStaff(enggStaff);
    }

    // update Engg staff
    @PostMapping(value = "update")
    @ResponseBody
    public ServerResponse updateEnggStaff(@RequestBody EnggStaff enggstaff) {
        return enggStaffService.updateEnggStaff(enggstaff);
    }

    // get all Engg staff
    @GetMapping(value = "list")
    @ResponseBody
    public ServerResponse getAllEnggStaff() {
        return enggStaffService.listEnggStaff();
    }

    // search an engg staff
    @PostMapping(value = "search")
    @ResponseBody
    public ServerResponse searchEnggStaff(@RequestBody JSONObject jsonObj) {
        return enggStaffService.searchEnggStaff(jsonObj);
    }

    // delete an engg staff
    @DeleteMapping(value = "delete/{uid}")
    @ResponseBody
    public ServerResponse deleteEnggStaff(@PathVariable long uid) {
        return enggStaffService.deleteEnggStaff(uid);
    }

}
