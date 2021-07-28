package com.innowing.info.controller.primary.project;

import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.primary.project.Project;
import com.innowing.info.entity.primary.project.ProjectCategory;
import com.innowing.info.service.primary.poject.ProjectCategoryService;
import com.innowing.info.service.primary.poject.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ Author     ：Edison
 * @ Date       ：Created in 12:13 2021/7/16
 * @ Description：Project Controller
 * @ Modified By：
 * @Version: 1.0
 */
@Controller
@RequestMapping(path = "project-category")
public class ProjectCategoryController {
    private final ProjectCategoryService projectCategoryService;
    @Autowired
    public ProjectCategoryController(ProjectCategoryService projectCategoryService) {
        this.projectCategoryService = projectCategoryService;
    }

    @PostMapping
    @ResponseBody
    ServerResponse createProjectCategory(@RequestBody ProjectCategory projectCategory) {
        return projectCategoryService.createProjectCategory(projectCategory);
    }

    @GetMapping("{id}")
    @ResponseBody
    ServerResponse getProjectCategoryById(@PathVariable Long id) {
        return projectCategoryService.getProjectCategoryById(id);
    }

    @GetMapping
    @ResponseBody
    ServerResponse getProjectCategories() { return projectCategoryService.getProjectCategories(); }

    @PatchMapping("{id}")
    @ResponseBody
    ServerResponse updateProjectCategoryById(@PathVariable Long id, @RequestBody ProjectCategory projectCategory){
        return projectCategoryService.updateProjectCategoryById(id, projectCategory);
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public ServerResponse deleteProjectCategoryById(@PathVariable long id) {
        return projectCategoryService.deleteProjectCategoryById(id);
    }
}
