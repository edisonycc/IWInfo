package com.innowing.info.repository.primary.project;

import com.innowing.info.entity.primary.project.Project;
import com.innowing.info.entity.primary.project.ProjectStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProjectStaffRepository extends JpaRepository<ProjectStaff, Long> {
    @Transactional
    @Modifying
    @Query("delete from ProjectStaff")
    void deleteAllProjectStaffs();
}
