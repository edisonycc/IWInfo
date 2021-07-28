package com.innowing.info.repository.primary.project;

import com.innowing.info.entity.primary.EligibleStudent;
import com.innowing.info.entity.primary.project.Project;
import com.innowing.info.entity.primary.project.ProjectStudent;
import com.innowing.info.entity.primary.project.ProjectStudentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectStudentRepository extends JpaRepository<ProjectStudent, Long> {
    void deleteById(ProjectStudentId id);
    Optional<List<ProjectStudent>> getProjectStudentByProject_Id(Long projectId);
    @Modifying
    @Transactional
    @Query("delete from ProjectStudent ps where ps.project.id = ?1 and ps.eligibleStudent.id =?2")
    void deleteById_ProjectIdAndId_EligibleStudentId(Long projectId, Long eligibleStudentId);
}

