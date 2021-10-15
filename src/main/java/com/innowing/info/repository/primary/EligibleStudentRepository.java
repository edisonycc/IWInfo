package com.innowing.info.repository.primary;

import com.innowing.info.entity.primary.EligibleStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface EligibleStudentRepository extends JpaRepository<EligibleStudent, Long> {
    Optional<EligibleStudent> findEligibleStudentByHkuId(Long hkuId);
    Optional<EligibleStudent> findEligibleStudentByEmail(String email);
    Optional<List<EligibleStudent>> findEligibleStudentByEmailStartingWith (String emailPrefix);
    // EligibleStudent getEligibleStudentByHkuId(Long hkuId);
    // List<EligibleStudent> findEligibleStudentByHkuIdContaining(String hkuId);
    @Transactional
    void deleteByHkuId (Long hkuId);

    //    @Modifying
//    @Query("update EligibleStudent student set student.isActive =?1 where student.hkuId =?2")
//    void setIsActiveForAll(Boolean isActive, Long hkuId);

    @Transactional
    @Modifying
    @Query("update EligibleStudent student set student.isActive = false where student.hkuId > 0")
    void setAllStudentInactive();
}
