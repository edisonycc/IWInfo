package com.innowing.info.service.primary.poject;

import com.innowing.info.common.ResponseEnum;
import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.primary.project.Project;
import com.innowing.info.entity.primary.project.ProjectCategory;
import com.innowing.info.repository.primary.project.ProjectCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class ProjectCategoryService {
    @Autowired
    ProjectCategoryRepository projectCategoryRepository;

    public ServerResponse createProjectCategory(ProjectCategory projectCategory) {
        try {
            // handling edge cases needed
            projectCategoryRepository.save(projectCategory);
            return ServerResponse.
                    getInstance().
                    responseEnum(ResponseEnum.ADD_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance()
                    .responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    public ServerResponse getProjectCategoryById(Long id) {
        Optional<ProjectCategory> projectCategoryOptional = projectCategoryRepository.findById(id);
        if (projectCategoryOptional.isPresent())
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data("projectCategory", projectCategoryOptional.get());
        return ServerResponse.getInstance().responseEnum(ResponseEnum.FAILED);
    }

    public ServerResponse getProjectCategories() {
        List<ProjectCategory> projectCategories = projectCategoryRepository.findAll();
        return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data("projectCategoryList", projectCategories);
    }

    public ServerResponse deleteProjectCategoryById(long id) {
        try {
            projectCategoryRepository.deleteById(id);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_SUCCESS);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_FAILED);
        }
    }

    public ServerResponse updateProjectCategoryById(Long id, ProjectCategory projectCategory) {
        try {
            Optional<ProjectCategory> projectCategoryOptional = projectCategoryRepository.findById(id);
            if (projectCategoryOptional.isPresent()) {
                ProjectCategory _projectCategory = projectCategoryOptional.get();
                if (projectCategory != null && !Objects.equals(_projectCategory.getCategoryName(), projectCategory.getCategoryName())) {
                    _projectCategory.setCategoryName(projectCategory.getCategoryName());
                }
                projectCategoryRepository.save(_projectCategory);
                return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_SUCCESS);
            }
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR); // need to add not found error
        } catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }
}
