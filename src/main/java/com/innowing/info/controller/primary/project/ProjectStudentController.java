package com.innowing.info.controller.primary.project;

import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.primary.EligibleStudent;
import com.innowing.info.entity.primary.project.ProjectStudent;
import com.innowing.info.service.primary.poject.ProjectStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ Author     ：Edison
 * @ Date       ：Created in 12:13 2021/7/5
 * @ Description：Project Student Association
 * @ Modified By：
 * @Version: 1.0
 */
@Controller
@RequestMapping(path = "api/project-student")
public class ProjectStudentController {
    private final ProjectStudentService projectStudentService;
    @Autowired
    public ProjectStudentController(ProjectStudentService projectStudentService) {
        this.projectStudentService = projectStudentService;
    }

    @PostMapping
    @ResponseBody
    ServerResponse createProjectStudent(@RequestBody ProjectStudent projectStudent) {
        return projectStudentService.createProjectStudent(projectStudent);
    }

    @PatchMapping("list")
    @ResponseBody
    ServerResponse updateProjectStudentList(@RequestBody List<ProjectStudent> projectStudentList){
        return projectStudentService.updateProjectStudentList(projectStudentList);
    }
}
