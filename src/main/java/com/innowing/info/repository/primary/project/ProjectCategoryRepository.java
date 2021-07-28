package com.innowing.info.repository.primary.project;

import com.innowing.info.entity.primary.project.ProjectCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectCategoryRepository extends JpaRepository<ProjectCategory, Long> {
}
