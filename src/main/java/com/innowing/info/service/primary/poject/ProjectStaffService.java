package com.innowing.info.service.primary.poject;

import com.innowing.info.common.ResponseEnum;
import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.primary.project.ProjectStaff;
import com.innowing.info.repository.primary.project.ProjectStaffRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProjectStaffService {
    @Autowired
    ProjectStaffRepository projectStaffRepository;

    public ServerResponse createProjectStaff(ProjectStaff projectStaff) {
        try {
            // handling edge cases needed
            projectStaffRepository.save(projectStaff);
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
