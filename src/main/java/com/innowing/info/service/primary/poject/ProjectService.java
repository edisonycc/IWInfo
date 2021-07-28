package com.innowing.info.service.primary.poject;

import com.innowing.info.common.ResponseEnum;
import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.primary.EligibleStudent;
import com.innowing.info.entity.primary.project.*;
import com.innowing.info.repository.primary.EligibleStudentRepository;
import com.innowing.info.repository.primary.StaffRepository;
import com.innowing.info.repository.primary.project.ProjectCategoryRepository;
import com.innowing.info.repository.primary.project.ProjectRepository;
import com.innowing.info.repository.primary.project.ProjectStudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectStudentRepository projectStudentRepository;

    @Autowired ProjectStudentService projectStudentService;

    @Autowired
    ProjectCategoryRepository projectCategoryRepository;

    @Autowired
    EligibleStudentRepository eligibleStudentRepository;
    @Autowired
    StaffRepository staffRepository;

    public ServerResponse createProject(Project project) {
        try {
            // handling edge cases needed
            Project newProject = new Project();
            if (project.getCategory() != null) {
                newProject.setCategory(projectCategoryRepository.getOne(project.getCategory().getId()));
            }
            if (project.getTitle() != null) {
                newProject.setTitle(project.getTitle());
            }
            if (project.getInnoUniqueness() != null) {
                newProject.setInnoUniqueness(project.getInnoUniqueness());
            }
            if (project.getHostDept() != null) {
                newProject.setHostDept(project.getHostDept());
            }
            if (project.getStartDate() != null) {
                newProject.setStartDate(project.getStartDate());
            }
            if (project.getEndDate() != null) {
                newProject.setEndDate(project.getEndDate());
            }
            if (project.getSummary() != null) {
                newProject.setSummary(project.getSummary());
            }
            newProject = projectRepository.save(newProject);
//            log.info(newProject.toString());
            if (project.getProjectStudents() != null) {
                List<ProjectStudent> newProjectStudentList = new ArrayList<>();
                for (ProjectStudent ps : project.getProjectStudents()) {
                    ProjectStudentId projectStudentId = new ProjectStudentId(
                            newProject.getId(),
                            ps.getId().getEligibleStudentId()
                    );
                    ProjectStudent _projectStudent = new ProjectStudent(
                            projectStudentId,
//                            projectRepository.getOne(newProject.getId()),
                            newProject,
                            eligibleStudentRepository.getOne(ps.getId().getEligibleStudentId()),
                            ps.getRole()
                    );
                    newProjectStudentList.add(_projectStudent);
                }
                newProject.setProjectStudents(newProjectStudentList);
                projectRepository.save(newProject);
            }
            if (project.getProjectStaffs() != null) {
                List<ProjectStaff> newProjectStaffList = new ArrayList<>();
                for (ProjectStaff ps : project.getProjectStaffs()) {
                    ProjectStaffId projectStaffId = new ProjectStaffId(
                            newProject.getId(),
                            ps.getId().getStaffId()
                    );
                    ProjectStaff _projectStaff = new ProjectStaff(
                            projectStaffId,
//                            projectRepository.getOne(newProject.getId()),
                            newProject,
                            staffRepository.getOne(ps.getId().getStaffId()),
                            ps.getRole()
                    );
                    newProjectStaffList.add(_projectStaff);
                }
                newProject.setProjectStaffs(newProjectStaffList);
                projectRepository.save(newProject);
            }
            return ServerResponse.
                    getInstance().
                    responseEnum(ResponseEnum.ADD_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance()
                    .responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    public ServerResponse getProjectById(Long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent())
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data("project", projectOptional.get());
        return ServerResponse.getInstance().responseEnum(ResponseEnum.FAILED);
    }

    public ServerResponse getProjectByCategoryId(Long categoryId) {
        Optional<List<Project>> projectsOptional = projectRepository.findByCategory_Id(categoryId);
        if (projectsOptional.isPresent())
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data("projects", projectsOptional.get());
        return ServerResponse.getInstance().responseEnum(ResponseEnum.FAILED);
    }

    public ServerResponse getProjects() {
        List<Project> projects = projectRepository.findAll();
        return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data("projectList", projects);
    }

    public ServerResponse deleteProjectById(long id) {
        try {
            projectRepository.deleteById(id);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_SUCCESS);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_FAILED);
        }
    }

    @Transactional
    @Modifying
    public ServerResponse updateProjectById(Long id, Project project) {
        try {
            Optional<Project> projectOptional = projectRepository.findById(id);
            if (projectOptional.isPresent()) {
                Project _project = projectOptional.get();
                if (project.getTitle() != null && !Objects.equals(_project.getTitle(), project.getTitle())) {
                    _project.setTitle(project.getTitle());
                }
                if (project.getTechTheme() != null && !Objects.equals(_project.getTechTheme(), project.getTechTheme())) {
                    _project.setTechTheme(project.getTechTheme());
                }
                if (project.getHostDept() != null && !Objects.equals(_project.getHostDept(), project.getHostDept())) {
                    _project.setHostDept(project.getHostDept());
                }
                if (project.getStartDate() != null && !Objects.equals(_project.getStartDate(), project.getStartDate())) {
                    _project.setStartDate(project.getStartDate());
                }
                if (project.getEndDate() != null && !Objects.equals(_project.getEndDate(), project.getEndDate())) {
                    _project.setEndDate(project.getEndDate());
                }
                if (project.getSummary() != null && !Objects.equals(_project.getSummary(), project.getSummary())) {
                    _project.setSummary(project.getSummary());
                }
                if (project.getInnoUniqueness() != null && !Objects.equals(_project.getInnoUniqueness(), project.getInnoUniqueness())) {
                    _project.setInnoUniqueness(project.getInnoUniqueness());
                }
                if (project.getCategory() != null && !Objects.equals(_project.getCategory(), project.getCategory())) {
//                    _project.setCategory(project.getCategory());
                    log.info(project.getCategory().getId().toString());
                    _project.setCategory(projectCategoryRepository.getOne(project.getCategory().getId()));
                }
                if (project.getProjectStudents() != null && !Objects.equals(_project.getProjectStudents(), project.getProjectStudents())) {
                    List<ProjectStudent> projectStudentsList = project.getProjectStudents();
                    List<ProjectStudent> newProjectStudentList = new ArrayList<>();
                    for (ProjectStudent ps : projectStudentsList) {
                         ProjectStudentId projectStudentId = new ProjectStudentId(
                                 ps.getId().getProjectId(),
                                 ps.getId().getEligibleStudentId()
                         );
                         ProjectStudent _projectStudent = new ProjectStudent(
                                 projectStudentId,
                                 projectRepository.getOne(ps.getId().getProjectId()),
                                 eligibleStudentRepository.getOne(ps.getId().getEligibleStudentId()),
                                 ps.getRole()
                         );
                         newProjectStudentList.add(_projectStudent);
                        log.info(newProjectStudentList.toString());
                    }
                    _project.setProjectStudents(newProjectStudentList);
                    log.info( _project.getProjectStudents().toString());
                }
                if (project.getProjectStaffs() != null && !Objects.equals(_project.getProjectStaffs(), project.getProjectStaffs())) {
                    List<ProjectStaff> projectStaffList = project.getProjectStaffs();
                    List<ProjectStaff> newProjectStaffList = new ArrayList<>();
                    for (ProjectStaff ps : projectStaffList) {
                        ProjectStaffId projectStaffId = new ProjectStaffId(
                                ps.getId().getProjectId(),
                                ps.getId().getStaffId()
                        );
                        ProjectStaff _projectStaff = new ProjectStaff(
                                projectStaffId,
                                projectRepository.getOne(ps.getId().getProjectId()),
                                staffRepository.getOne(ps.getId().getStaffId()),
                                ps.getRole()
                        );
                        newProjectStaffList.add(_projectStaff);
                        log.info(newProjectStaffList.toString());
                    }
                    _project.setProjectStaffs(newProjectStaffList);
                    log.info( _project.getProjectStaffs().toString());
                }
                projectRepository.save(_project);
                return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_SUCCESS);
            }
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR); // need to add not found error
        } catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

}
