package com.innowing.info.repository.primary.project;

import com.innowing.info.entity.primary.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    public Optional<Project> findProjectByTitle(String title);
}
