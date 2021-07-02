package com.innowing.info.service.primary;

import com.innowing.info.common.ResponseEnum;
import com.innowing.info.common.ServerResponse;
import com.innowing.info.model.StaffPage;
import com.innowing.info.model.StaffSearchCriteria;
import com.innowing.info.repository.primary.StaffCriteriaRepository;
import com.innowing.info.repository.primary.StaffRepository;
import com.innowing.info.entity.primary.Staff;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Slf4j
public class StaffService {


    private final StaffRepository staffRepository;

    @Autowired
    StaffCriteriaRepository staffCriteriaRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }


    public ServerResponse createStaff(Staff staff) {
        Optional<Staff> staffOptional =  staffRepository.findStaffByHkuId(staff.getHkuId());
        if (staffOptional.isPresent())
            return ServerResponse.getInstance().responseEnum(ResponseEnum.USERNAME_EXIST);
        try {
            staffRepository.save(staff);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance()
                    .responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    @Transactional
    public ServerResponse deleteStaff(Long hkuId) {
//        boolean exists = staffRepository.existsById(hkuId);
//        if (!exists) {
//         throw new IllegalStateException(
//                 "user with id " + hkuId + " does not exists");
//        }
//        staffRepository.deleteById(hkuId);
        try {
            staffRepository.deleteByHkuId(hkuId);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_SUCCESS);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_FAILED);
        }
    }

    @Transactional
    public ServerResponse updateStaff(Long hkuId, Staff staff) {
        try {
//            Long id = eligibleStudent.getId();
            Optional<Staff> _staff = staffRepository.findStaffByHkuId(hkuId);

            if(_staff.isPresent()) {
                if (staff.getHkuId() != null  && !Objects.equals(_staff.get().getHkuId(), staff.getHkuId()))
                    _staff.get().setHkuId(staff.getHkuId());
                if (staff.getName() != null  && !Objects.equals(_staff.get().getName(), staff.getName()))
                    _staff.get().setName(staff.getName());
                if (staff.getEmail() != null  && !Objects.equals(_staff.get().getEmail(), staff.getEmail()))
                    _staff.get().setEmail(staff.getEmail());
                if (staff.getCardId() != null  && !Objects.equals(_staff.get().getCardId(), staff.getCardId()))
                    _staff.get().setCardId(staff.getCardId());
                if (staff.getFaculty() != null  && !Objects.equals(_staff.get().getFaculty(), staff.getFaculty()))
                    _staff.get().setFaculty(staff.getFaculty());
                if (staff.getDepartment() != null  && !Objects.equals(_staff.get().getDepartment(), staff.getDepartment()))
                    _staff.get().setDepartment(staff.getDepartment());
                if (staff.getStaffCategory() != null  && !Objects.equals(_staff.get().getStaffCategory(), staff.getStaffCategory()))
                    _staff.get().setStaffCategory(staff.getStaffCategory());
                if (staff.getTitle() != null  && !Objects.equals(_staff.get().getTitle(), staff.getTitle()))
                    _staff.get().setTitle(staff.getTitle());
//            _student.setMemberStatus(eligibleStudent.getMemberStatus());

                if (staff.getAccessGranted() != null  && !Objects.equals(_staff.get().getAccessGranted(), staff.getAccessGranted()))
                    _staff.get().setAccessGranted(staff.getAccessGranted());

                staffRepository.save(_staff.get());
                return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_SUCCESS);
            }

            staffRepository.save(staff);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.SUCCESS);
        } catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

//    @Transactional
//    public void updateStaff(Long staffId, String name, String email) {
//        Staff staff = staffRepository.findById(staffId).orElseThrow(() -> new IllegalStateException(
//                "user with id " + staffId + " does not exist"
//        ));
//
//        if (name != null  && name.length() > 0 && !Objects.equals(staff.getName(), name))  {
//            staff.setName(name);
//        }
//
//        if (email!= null  && email.length() > 0 && !Objects.equals(staff.getEmail(), email)) {
//            Optional<Staff> userByEmail = staffRepository.findUserByEmail(email);
//            if (userByEmail.isPresent()) {
//                throw new IllegalStateException("email taken");
//            }
//            staff.setEmail(email);
//        }
//
//    }

    public ServerResponse getStaffs(StaffPage staffPage, StaffSearchCriteria staffSearchCriteria) {
        // front end return 1 -> backend use 0
        int currentPageNumber = staffPage.getPageNumber();
        if (currentPageNumber > 0)  staffPage.setPageNumber(currentPageNumber - 1);
        Page<Staff> staffPage1 = staffCriteriaRepository.findAllWithFilters(staffPage, staffSearchCriteria);
        return ServerResponse.getInstance().responseEnum(ResponseEnum.SUCCESS).data("pageObject", staffPage1);

    }

    @Transactional
    public ServerResponse updateStaffList(List<Staff> staffList) {
        staffList.forEach(staff -> updateStaff(staff.getHkuId(), staff));
        return ServerResponse.getInstance().responseEnum(ResponseEnum.SUCCESS);
    }
}
