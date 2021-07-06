package com.innowing.info.controller.primary.project;

import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.primary.project.ProjectStaff;
import com.innowing.info.entity.primary.project.ProjectStudent;
import com.innowing.info.service.primary.poject.ProjectStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ Author     ：Edison
 * @ Date       ：Created in 12:13 2021/7/5
 * @ Description：Project Staff Association Controller
 * @ Modified By：
 * @Version: 1.0
 */
@Controller
@RequestMapping(path = "api/project-staff")
public class ProjectStaffController {
    private final ProjectStaffService projectStaffService;
    @Autowired
    public ProjectStaffController(ProjectStaffService projectStaffService) {
        this.projectStaffService = projectStaffService;
    }

    @PostMapping
    @ResponseBody
    ServerResponse createProjectStaff(@RequestBody ProjectStaff projectStaff) {
        return projectStaffService.createProjectStaff(projectStaff);
    }
}
