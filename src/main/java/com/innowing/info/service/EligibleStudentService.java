package com.innowing.info.service;

import com.innowing.info.common.ResponseEnum;
import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.EligibleStudent;
import com.innowing.info.entity.Student;
import com.innowing.info.model.EligibleStudentPage;
import com.innowing.info.model.EligibleStudentSearchCriteria;
import com.innowing.info.repository.EligibleStudentCriteriaRepository;
import com.innowing.info.repository.EligibleStudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EligibleStudentService {
    @Autowired
    EligibleStudentRepository eligibleStudentRepository;
    @Autowired
    EligibleStudentCriteriaRepository eligibleStudentCriteriaRepository;
//    @Autowired
//    EligibleStudentCriteriaRepository eligiblestudentCriteriaRepository;

    public ServerResponse createEligibleStudent(EligibleStudent eligibleStudent) {
        Optional<EligibleStudent> eligibleStudentOptional =  eligibleStudentRepository.findEligibleStudentByHkuId(eligibleStudent.getHkuId());
        if (eligibleStudentOptional.isPresent())
            return ServerResponse.getInstance().responseEnum(ResponseEnum.USERNAME_EXIST);
        try {
            eligibleStudentRepository.save(eligibleStudent);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance()
                    .responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    public ServerResponse getEligibleStudents(EligibleStudentPage eligibleStudentPage,
                                              EligibleStudentSearchCriteria eligibleStudentSearchCriteria) {
        // front end return 1 -> backend use 0
        int currentPageNumber = eligibleStudentPage.getPageNumber();
        if (currentPageNumber > 0)  eligibleStudentPage.setPageNumber(currentPageNumber - 1);
        Page<EligibleStudent> studentPage1 = eligibleStudentCriteriaRepository.findAllWithFilters(eligibleStudentPage, eligibleStudentSearchCriteria);
        return ServerResponse.getInstance().responseEnum(ResponseEnum.SUCCESS).data("pageObject", studentPage1);

    }

    public ServerResponse getEligibleStudentByHkuId(Long hkuId) {
        Optional<EligibleStudent> eligibleStudents = eligibleStudentRepository.findEligibleStudentByHkuId(hkuId);
        if (!eligibleStudents.isEmpty())
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data("eligibleStudents", eligibleStudents);
        return ServerResponse.getInstance().responseEnum(ResponseEnum.FAILED);
    }

    public ServerResponse updateEligibleStudent(EligibleStudent eligibleStudent) {
        try {
            Long id = eligibleStudent.getId();
            EligibleStudent _student = eligibleStudentRepository.getOne(id);

            _student.setHkuId(eligibleStudent.getHkuId());
            _student.setName(eligibleStudent.getName());
            _student.setEmail(eligibleStudent.getEmail());
            _student.setCardId(eligibleStudent.getCardId());
            _student.setStudyYear(eligibleStudent.getStudyYear());
            _student.setFaculty(eligibleStudent.getFaculty());
            _student.setDepartment(eligibleStudent.getDepartment());
            _student.setCurriculum(eligibleStudent.getCurriculum());
            _student.setTitle(eligibleStudent.getTitle());
//            _student.setMemberStatus(eligibleStudent.getMemberStatus());
            _student.setAppliedMembership(eligibleStudent.getAppliedMembership());
            _student.setAgreedToPay(eligibleStudent.getAgreedToPay());
            _student.setSiteVisited(eligibleStudent.getSiteVisited());
            _student.setPassedQuiz(eligibleStudent.getPassedQuiz());
            _student.setDepositPaid(eligibleStudent.getDepositPaid());
            _student.setAccessGranted(eligibleStudent.getAccessGranted());

            eligibleStudentRepository.save(_student);

            return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_SUCCESS);
        } catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    public ServerResponse deleteStudent(long hkuId) {
        try {
            eligibleStudentRepository.deleteByHkuId(hkuId);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_SUCCESS);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_FAILED);
        }
    }
}
