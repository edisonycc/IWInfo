package com.innowing.info.repository;


import com.innowing.info.entity.Staff;
import com.innowing.info.entity.Student;
import com.innowing.info.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findStudentByHkuId(Long hkuId);
    @Transactional
    void deleteByHkuId (Long hkuId);

//    Optional<Student> findStudentBy
}
