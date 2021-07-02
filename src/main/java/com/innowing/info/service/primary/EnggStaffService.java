package com.innowing.info.service.primary;

import com.alibaba.fastjson.JSONObject;
import com.innowing.info.common.ResponseEnum;
import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.primary.EnggStaff;
import com.innowing.info.repository.primary.EnggStaffRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EnggStaffService {


    private final EnggStaffRepository enggStaffRepository;

    @Autowired
    public EnggStaffService(EnggStaffRepository enggStaffRepository) {
        this.enggStaffRepository = enggStaffRepository;
    }

    public ServerResponse addEnggStaff(EnggStaff enggStaff) {
        try {
            enggStaffRepository.save(enggStaff);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.ADD_SUCCESS);
        }catch (Exception e) {
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }


    public ServerResponse updateEnggStaff(EnggStaff enggstaff) {
        try {
            long uid = enggstaff.getUid();
            EnggStaff _enggStaff = enggStaffRepository.findEnggStaffByUid(uid);
            _enggStaff.setEmail(enggstaff.getEmail());
            _enggStaff.setStaffCategory(enggstaff.getStaffCategory());
            _enggStaff.setCardId(enggstaff.getCardId());
            _enggStaff.setDepartment(enggstaff.getDepartment());
            _enggStaff.setFirstName(enggstaff.getFirstName());
            _enggStaff.setLastName(enggstaff.getLastName());
            _enggStaff.setTitle(enggstaff.getTitle());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_SUCCESS);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_FAILED);
        }
    }

    public ServerResponse listEnggStaff() {
        try {
            List<EnggStaff> enggStaffs = enggStaffRepository.findAll();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data("enggStaff", enggStaffs);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    public ServerResponse searchEnggStaff(JSONObject obj) {
        try {
            String select = obj.getString("select");
            String content = obj.getString("content");
            List<EnggStaff> enggStaffs = new ArrayList<>();
            switch (select){
                case "UID":
                    enggStaffs.add(enggStaffRepository.findEnggStaffByUid(Long.parseLong(content))) ;
                    break;
                case "First Name":
                    enggStaffs = enggStaffRepository.findByFirstNameIsContaining(content);
                    break;
                default:
                    break;
            }
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data("enggStaff", enggStaffs);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    public ServerResponse deleteEnggStaff(long uid) {
        try {
            enggStaffRepository.deleteByUid(uid);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_SUCCESS);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_FAILED);
        }
    }
}
