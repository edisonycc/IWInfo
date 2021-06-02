package com.innowing.info.controller;


import com.innowing.info.entity.Staff;
import com.innowing.info.service.StaffService;
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

    @GetMapping
    public List<Staff> getAllStaff() {
        return staffService.getAllUser();
    }

    @PostMapping
    public void addEnggStaff(@RequestBody Staff staff) {
        staffService.addNewUser(staff);
    }

    @DeleteMapping(path = "{staffId}")
    public void deleteUser(@PathVariable("staffId") Long staffId) {
        staffService.deleteUser(staffId);
    }

    @PutMapping(path = "{staffId}")
    public void updateUser (
            @PathVariable("staffId") Long staffId,
            @RequestParam(required = false) String name,
            //@RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email
    ) {
        staffService.updateStaff(staffId, name, email);
    }


}
