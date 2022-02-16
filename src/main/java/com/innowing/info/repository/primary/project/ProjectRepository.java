package com.innowing.info.repository.primary.project;

import com.innowing.info.entity.primary.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    public Optional<Project> findProjectByTitle(String title);
    public Optional<List<Project>> findByCategory_Id(Long categoryId);
    //public Optional<Project> findProjectById(Long id);
}
