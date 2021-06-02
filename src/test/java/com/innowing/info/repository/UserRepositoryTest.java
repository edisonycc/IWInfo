package com.innowing.info.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private StaffRepository userRepository;

    @Test
    void findAll() {
        System.out.println(userRepository.findAll());

    }
}