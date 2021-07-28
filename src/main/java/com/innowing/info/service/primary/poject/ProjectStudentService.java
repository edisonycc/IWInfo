package com.innowing.info.service.primary.poject;

import com.innowing.info.common.ResponseEnum;
import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.primary.EligibleStudent;
import com.innowing.info.entity.primary.project.Project;
import com.innowing.info.entity.primary.project.ProjectStudent;
import com.innowing.info.entity.primary.project.ProjectStudentId;
import com.innowing.info.repository.primary.EligibleStudentRepository;
import com.innowing.info.repository.primary.project.ProjectRepository;
import com.innowing.info.repository.primary.project.ProjectStudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class ProjectStudentService {
    @Autowired
    ProjectStudentRepository projectStudentRepository;
    @Autowired
    EligibleStudentRepository eligibleStudentRepository;
    @Autowired
    ProjectRepository projectRepository;

    public ServerResponse createProjectStudent(ProjectStudent projectStudent) {
        try {
            // handling edge cases needed
            projectStudentRepository.save(projectStudent);
            return ServerResponse.
                    getInstance().
                    responseEnum(ResponseEnum.ADD_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance()
                    .responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    public ServerResponse updateProjectStudentList(List<ProjectStudent> projectStudentList) {
        try {
            projectStudentList.forEach(projectStudent -> {
                String email = projectStudent.getEligibleStudent().getEmail();
                String role = projectStudent.getRole();
                if (!Objects.isNull(email)) {
                    Optional<EligibleStudent> studentOptional = eligibleStudentRepository.findEligibleStudentByEmail(email);
                    if (!studentOptional.isPresent()) {
                        EligibleStudent eligibleStudent = projectStudent.getEligibleStudent();
                        eligibleStudentRepository.save(eligibleStudent);
                    }
                    String projectTitle = projectStudent.getProject().getTitle();
                    if (studentOptional.isPresent() && !Objects.isNull(projectTitle)) {
                        Optional<Project> projectOptional = projectRepository.findProjectByTitle(projectTitle);
                        if (!projectOptional.isPresent()) {
                            Project project = projectStudent.getProject();
                            projectRepository.save(project);
                            projectOptional = projectRepository.findProjectByTitle(project.getTitle());
                        }
                        if (projectOptional.isPresent()) {
                            ProjectStudentId projectStudentId = new ProjectStudentId(projectOptional.get().getId(),
                                    studentOptional.get().getId());
                            ProjectStudent _projectStudent = new ProjectStudent(projectStudentId, projectOptional.get(), studentOptional.get(), role);
                            projectStudentRepository.save(_projectStudent);
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

    @Modifying
    @Transactional
    public ServerResponse deleteProjectStudent(long projectId, long eligibleStudentId) {
        try {
            projectStudentRepository.deleteById_ProjectIdAndId_EligibleStudentId(projectId,eligibleStudentId);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_SUCCESS);
        } catch(Exception e) {
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.FAILED);
        }
    }

    public ServerResponse getProjectStudentsByProjectId(Long projectId) {
        Optional<List<ProjectStudent>> ProjectStudentsListOptional = projectStudentRepository.getProjectStudentByProject_Id(projectId);
        if (ProjectStudentsListOptional.isPresent())
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data("projectStudentList", ProjectStudentsListOptional.get());
        return ServerResponse.getInstance().responseEnum(ResponseEnum.FAILED);
    }
}
