package com.innowing.info.controller.primary.project;

import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.primary.EligibleStudent;
import com.innowing.info.entity.primary.project.Project;
import com.innowing.info.service.primary.poject.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ Author     ：Edison
 * @ Date       ：Created in 12:13 2021/7/5
 * @ Description：Project Controller
 * @ Modified By：
 * @Version: 1.0
 */
@Controller
@RequestMapping(path = "project")
public class ProjectController {
    private final ProjectService projectService;
    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    @ResponseBody
    ServerResponse createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    @GetMapping("{id}")
    @ResponseBody
    ServerResponse getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("category")
    @ResponseBody
    ServerResponse getProjectByCategoryId(@RequestParam Long id) {
        return projectService.getProjectByCategoryId(id);
    }

    @GetMapping
    @ResponseBody
    ServerResponse getProjects() { return projectService.getProjects(); }

    @PatchMapping("{id}")
    @ResponseBody
    ServerResponse updateProjectById(@PathVariable Long id, @RequestBody Project project){
        return projectService.updateProjectById(id, project);
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public ServerResponse deleteProjectById(@PathVariable long id) {
        return projectService.deleteProjectById(id);
    }

}
