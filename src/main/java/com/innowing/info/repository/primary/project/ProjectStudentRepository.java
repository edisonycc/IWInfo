package com.innowing.info.repository.primary.project;

import com.innowing.info.entity.primary.project.Project;
import com.innowing.info.entity.primary.project.ProjectStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectStudentRepository extends JpaRepository<ProjectStudent, Long> {
}
