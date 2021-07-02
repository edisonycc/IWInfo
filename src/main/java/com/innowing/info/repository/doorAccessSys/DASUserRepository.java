package com.innowing.info.repository.doorAccessSys;

import com.innowing.info.entity.doorAccessSys.DASUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DASUserRepository extends JpaRepository<DASUser, Integer> {
    DASUser findDASUserByHkuId(String hkuId);

    DASUser findDASUserById(int id);
}
