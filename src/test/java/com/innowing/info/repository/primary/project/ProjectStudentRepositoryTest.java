package com.innowing.info.repository.primary.project;


import com.innowing.info.entity.primary.project.ProjectStudent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;


import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProjectStudentRepositoryTest {

    @Autowired
    private ProjectStudentRepository projectStudentRepository;

    @Test
    void testDeleteAllProjectStudents() {
        projectStudentRepository.deleteAllProjectStudents();
        assertTrue(projectStudentRepository.findAll().isEmpty());
    }
}