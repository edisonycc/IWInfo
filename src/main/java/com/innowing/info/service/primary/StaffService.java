package com.innowing.info.service.primary;

import com.innowing.info.common.ResponseEnum;
import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.primary.EligibleStudent;
import com.innowing.info.entity.primary.facility.FacilityStaffId;
import com.innowing.info.entity.primary.facility.FacilityStaffSkill;
import com.innowing.info.entity.primary.facility.FacilityStudentId;
import com.innowing.info.entity.primary.facility.FacilityStudentSkill;
import com.innowing.info.model.StaffPage;
import com.innowing.info.model.StaffSearchCriteria;
import com.innowing.info.repository.primary.StaffCriteriaRepository;
import com.innowing.info.repository.primary.StaffRepository;
import com.innowing.info.entity.primary.Staff;
import com.innowing.info.repository.primary.facility.FacilityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    FacilityRepository facilityRepository;

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
    public ServerResponse updateStaff(Long id, Staff staff) {
        try {
//            Long id = eligibleStudent.getId();
            Optional<Staff> staffOptional = staffRepository.findById(id);

            if(staffOptional.isPresent()) {
                if (staff.getHkuId() != null  && !Objects.equals(staffOptional.get().getHkuId(), staff.getHkuId()))
                    staffOptional.get().setHkuId(staff.getHkuId());
                if (staff.getName() != null  && !Objects.equals(staffOptional.get().getName(), staff.getName()))
                    staffOptional.get().setName(staff.getName());
                if (staff.getEmail() != null  && !Objects.equals(staffOptional.get().getEmail(), staff.getEmail()))
                    staffOptional.get().setEmail(staff.getEmail());
                if (staff.getGender() != null  && !Objects.equals(staffOptional.get().getGender(), staff.getGender()))
                    staffOptional.get().setGender(staff.getGender());
                if (staff.getCardId() != null  && !Objects.equals(staffOptional.get().getCardId(), staff.getCardId()))
                    staffOptional.get().setCardId(staff.getCardId());
                if (staff.getFaculty() != null  && !Objects.equals(staffOptional.get().getFaculty(), staff.getFaculty()))
                    staffOptional.get().setFaculty(staff.getFaculty());
                if (staff.getDepartment() != null  && !Objects.equals(staffOptional.get().getDepartment(), staff.getDepartment()))
                    staffOptional.get().setDepartment(staff.getDepartment());
                if (staff.getTitle() != null  && !Objects.equals(staffOptional.get().getTitle(), staff.getTitle()))
                    staffOptional.get().setTitle(staff.getTitle());
                if (staff.getRole() != null  && !Objects.equals(staffOptional.get().getRole(), staff.getRole()))
                    staffOptional.get().setRole(staff.getRole());
                if (staff.getStaffCategory() != null  && !Objects.equals(staffOptional.get().getStaffCategory(), staff.getStaffCategory()))
                    staffOptional.get().setStaffCategory(staff.getStaffCategory());
                if (staff.getAccessGranted() != null  && !Objects.equals(staffOptional.get().getAccessGranted(), staff.getAccessGranted()))
                    staffOptional.get().setAccessGranted(staff.getAccessGranted());

                if (staff.getFacilityStaffSkills() != null
                        && !Objects.equals(staffOptional.get().getFacilityStaffSkills(), staff.getFacilityStaffSkills())
                ){
                    List<FacilityStaffSkill> facilityStaffSkillList = staff.getFacilityStaffSkills();
                    List<FacilityStaffSkill> newFacilityStaffSkillList = new ArrayList<>();
                    for (FacilityStaffSkill fss : facilityStaffSkillList) {
                        FacilityStaffId facilityStaffId = new FacilityStaffId(
                                fss.getId().getFacilityId(),
                                fss.getId().getStaffId()
                        );
                        FacilityStaffSkill _facilityStaffSkill = new FacilityStaffSkill(
                                facilityStaffId,
                                facilityRepository.getOne(facilityStaffId.getFacilityId()),
                                staffRepository.getOne(facilityStaffId.getStaffId()),
                                fss.getLevel()
                        );
                        newFacilityStaffSkillList.add(_facilityStaffSkill);
                    }
                    staffOptional.get().setFacilityStaffSkills(newFacilityStaffSkillList);
                    log.info(newFacilityStaffSkillList.toString());
                }

                staffRepository.save(staffOptional.get());
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

    public ServerResponse getStaffById(Long id) {
        Optional<Staff> staffOptional = staffRepository.findById(id);
        if (staffOptional.isPresent())
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data("staff", staffOptional.get());
        return ServerResponse.getInstance().responseEnum(ResponseEnum.FAILED);
    }

    public ServerResponse getStaffByUid(Long hkuId) {
        Optional<Staff> staffOptional = staffRepository.findStaffByHkuId(hkuId);
        if (staffOptional.isPresent())
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data("staff", staffOptional.get());
        return ServerResponse.getInstance().responseEnum(ResponseEnum.FAILED);
    }
}
