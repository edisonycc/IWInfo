package com.innowing.info.repository.primary;

import com.innowing.info.entity.primary.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    Optional<Staff> findUserByEmail(String email);

    Optional<Staff> findStaffByHkuId(Long hkuId);

    @Transactional
    void deleteByHkuId(Long hkuId);
}
