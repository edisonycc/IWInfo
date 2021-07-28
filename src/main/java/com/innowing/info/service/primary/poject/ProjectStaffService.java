package com.innowing.info.service.primary.poject;

import com.innowing.info.common.ResponseEnum;
import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.primary.Staff;
import com.innowing.info.entity.primary.project.Project;
import com.innowing.info.entity.primary.project.ProjectStaff;
import com.innowing.info.entity.primary.project.ProjectStaff;
import com.innowing.info.entity.primary.project.ProjectStaffId;
import com.innowing.info.repository.primary.StaffRepository;
import com.innowing.info.repository.primary.project.ProjectRepository;
import com.innowing.info.repository.primary.project.ProjectStaffRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class ProjectStaffService {
    @Autowired
    ProjectStaffRepository projectStaffRepository;
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    ProjectRepository projectRepository;

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

    public ServerResponse updateProjectStaffList(List<ProjectStaff> projectStaffList) {
        try {
            projectStaffList.forEach(projectStaff -> {
                String email = projectStaff.getStaff().getEmail();
                String role = projectStaff.getRole();
                if (!Objects.isNull(email)) {
                    // Check if needs find staff by email start with..
                    Optional<Staff> staffOptional = staffRepository.findStaffByEmail(email);
                    if (!staffOptional.isPresent()) {
                        Staff Staff = projectStaff.getStaff();
                        staffRepository.save(Staff);
                    }
                    String projectTitle = projectStaff.getProject().getTitle();
                    if (staffOptional.isPresent() && !Objects.isNull(projectTitle)) {
                        Optional<Project> projectOptional = projectRepository.findProjectByTitle(projectTitle);
                        if (!projectOptional.isPresent()) {
                            Project project = projectStaff.getProject();
                            projectRepository.save(project);
                            projectOptional = projectRepository.findProjectByTitle(project.getTitle());
                        }
                        if (projectOptional.isPresent()) {
                            ProjectStaffId projectStaffId = new ProjectStaffId(projectOptional.get().getId(),
                                    staffOptional.get().getId());
                            ProjectStaff _projectStaff = new ProjectStaff(projectStaffId, projectOptional.get(), staffOptional.get(), role);
                            projectStaffRepository.save(_projectStaff);
                        }
                        // throw exception needed
//                        else {
//                            throw Exception;
//                        }
                    }
                }
            });
            return ServerResponse.getInstance().responseEnum(ResponseEnum.ADD_SUCCESS);
        }
        catch (Exception e) {
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.FAILED);
        }
    }
}
