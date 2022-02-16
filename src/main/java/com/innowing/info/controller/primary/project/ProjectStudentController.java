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
@RequestMapping(path = "project-student")
public class ProjectStudentController {
    private final ProjectStudentService projectStudentService;
    @Autowired
    public ProjectStudentController(ProjectStudentService projectStudentService) {
        this.projectStudentService = projectStudentService;
    }

    @GetMapping("projectId")
    @ResponseBody
    public ServerResponse getProjectStudentsByProjectId(@RequestParam Long projectId) {
        return projectStudentService.getProjectStudentsByProjectId(projectId);
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

    @DeleteMapping("{projectId}/{eligibleStudentId}")
    @ResponseBody
    ServerResponse deleteProjectStudent(@PathVariable long projectId, @PathVariable long eligibleStudentId) {
        return projectStudentService.deleteProjectStudent(projectId, eligibleStudentId);
    }

    @DeleteMapping("deleteAllProjectStudents")
    @ResponseBody
    ServerResponse deleteAllProjectStudents() { return projectStudentService.deleteAllProjectStudents();}


}
