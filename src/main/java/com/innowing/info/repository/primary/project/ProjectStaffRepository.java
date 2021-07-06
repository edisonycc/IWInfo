package com.innowing.info.repository.primary.project;

import com.innowing.info.entity.primary.project.Project;
import com.innowing.info.entity.primary.project.ProjectStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectStaffRepository extends JpaRepository<ProjectStaff, Long> {
}
