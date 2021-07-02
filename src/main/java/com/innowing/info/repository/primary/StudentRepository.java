package com.innowing.info.repository.primary;


import com.innowing.info.entity.primary.Student;
import org.springframework.data.jpa.repository.JpaRepository;
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
