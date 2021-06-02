package com.innowing.info.repository;

import com.innowing.info.entity.EligibleStudent;
import com.innowing.info.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface EligibleStudentRepository extends JpaRepository<EligibleStudent, Long> {
    Optional<EligibleStudent> findEligibleStudentByHkuId(Long hkuId);
    List<EligibleStudent> findEligibleStudentByHkuIdContaining(String hkuId);
    @Transactional
    void deleteByHkuId (Long hkuId);
}
