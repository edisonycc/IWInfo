package com.innowing.info.service.doorAccessSys;

import com.innowing.info.common.ResponseEnum;
import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.doorAccessSys.DASUser;
import com.innowing.info.repository.doorAccessSys.DASUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DASUserService {
    private final DASUserRepository dasUserRepository;

    @Autowired
    public DASUserService(DASUserRepository dasUserRepository) {
        this.dasUserRepository = dasUserRepository;
    }

    public ServerResponse getDASUserByHkuId(long hkuId) {
        DASUser user = dasUserRepository.findDASUserByHkuId(String.valueOf(hkuId));

        return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data("user", user);
    }

    public ServerResponse getDASUserByDASUserId(Integer id) {
        DASUser user = dasUserRepository.findDASUserById(id);

        return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data("user", user);
    }

    public ServerResponse getDasUsers() {
        List<DASUser> dasUserList =  dasUserRepository.findAll();
        return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data("dasUserList", dasUserList);
    }
}