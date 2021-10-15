package com.innowing.info.controller.primary;


import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.primary.Staff;
import com.innowing.info.model.StaffPage;
import com.innowing.info.model.StaffSearchCriteria;
import com.innowing.info.service.primary.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "staff")
public class StaffController {

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping
    @ResponseBody
    public ServerResponse createStaff(@RequestBody Staff staff) {
        return staffService.createStaff(staff);
    }

    @GetMapping
    @ResponseBody
    public ServerResponse getStaffs(StaffPage staffPage,
                                    StaffSearchCriteria staffSearchCriteria) {
        return staffService.getStaffs(staffPage, staffSearchCriteria);
    }

    @GetMapping("id")
    @ResponseBody
    public ServerResponse getStaffById(@RequestParam Long id) {
        return staffService.getStaffById(id);
    }

    @GetMapping("hkuId")
    @ResponseBody
    public ServerResponse getStaffByUid(@RequestParam Long hkuId) {
        return staffService.getStaffByUid(hkuId);
    }

    @PatchMapping("{id}")
    @ResponseBody
    public ServerResponse updateStaff (@PathVariable Long id, @RequestBody Staff staff) {
        return staffService.updateStaff(id, staff);
    }

    @PatchMapping("list")
    @ResponseBody
    ServerResponse updateStaffList(@RequestBody List<Staff> staffList){
        return staffService.updateStaffList(staffList);
    }


    @DeleteMapping(path = "{hkuId}")
    public ServerResponse deleteStaff(@PathVariable long hkuId) {
        return staffService.deleteStaff(hkuId);
    }




}
