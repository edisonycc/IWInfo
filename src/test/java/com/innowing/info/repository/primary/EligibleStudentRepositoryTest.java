package com.innowing.info.repository.primary;

import com.innowing.info.entity.primary.EligibleStudent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EligibleStudentRepositoryTest {
    @Autowired
    EligibleStudentRepository eligibleStudentRepository;

}