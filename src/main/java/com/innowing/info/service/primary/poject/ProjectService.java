package com.innowing.info.service.primary.poject;

import com.innowing.info.common.ResponseEnum;
import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.primary.project.Project;
import com.innowing.info.repository.primary.project.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    public ServerResponse createProject(Project project) {
        try {
            // handling edge cases needed
            projectRepository.save(project);
            return ServerResponse.
                    getInstance().
                    responseEnum(ResponseEnum.ADD_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance()
                    .responseEnum(ResponseEnum.INNER_ERROR);
        }
    }
}
