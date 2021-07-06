package com.innowing.info.service.primary;

import com.innowing.info.common.ResponseEnum;
import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.primary.EligibleStudent;
import com.innowing.info.model.EligibleStudentPage;
import com.innowing.info.model.EligibleStudentSearchCriteria;
import com.innowing.info.repository.primary.EligibleStudentCriteriaRepository;
import com.innowing.info.repository.primary.EligibleStudentRepository;
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
public class EligibleStudentService {
    @Autowired
    EligibleStudentRepository eligibleStudentRepository;
    @Autowired
    EligibleStudentCriteriaRepository eligibleStudentCriteriaRepository;
//    @Autowired
//    EligibleStudentCriteriaRepository eligiblestudentCriteriaRepository;

    public ServerResponse createEligibleStudent(EligibleStudent eligibleStudent) {

//        Optional<EligibleStudent> eligibleStudentOptional = Optional.empty();
//        Optional<List<EligibleStudent>> eligibleStudentListOptional = Optional.empty();
//        if (Objects.nonNull(eligibleStudent.getHkuId())) {
//            eligibleStudentOptional =  eligibleStudentRepository.findEligibleStudentByHkuId(eligibleStudent.getHkuId());
//        } else if (Objects.nonNull(eligibleStudent.getEmail())){
//            String str = eligibleStudent.getEmail();
//            eligibleStudentListOptional =  eligibleStudentRepository.findEligibleStudentByEmailStartingWith(str.substring(0, str.indexOf("@")));
//        }
//
//        if (eligibleStudentOptional.isPresent() || eligibleStudentListOptional.isPresent())
//            return ServerResponse.getInstance().responseEnum(ResponseEnum.USERNAME_EXIST);
        try {
            eligibleStudentRepository.save(eligibleStudent);
            return ServerResponse.
                    getInstance().
                    responseEnum(ResponseEnum.SUCCESS);
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

    @Transactional
    public ServerResponse updateEligibleStudent(Long id, EligibleStudent eligibleStudent) {
        try {
//            Long id = eligibleStudent.getId();
            Optional<EligibleStudent> _studentOptional = eligibleStudentRepository.findById(id);

            if(_studentOptional.isPresent()) {
                if (eligibleStudent.getHkuId() != null  && !Objects.equals(_studentOptional.get().getHkuId(), eligibleStudent.getHkuId()))
                    _studentOptional.get().setHkuId(eligibleStudent.getHkuId());
                if (eligibleStudent.getName() != null  && !Objects.equals(_studentOptional.get().getName(), eligibleStudent.getName()))
                    _studentOptional.get().setName(eligibleStudent.getName());
                if (eligibleStudent.getEmail() != null  && !Objects.equals(_studentOptional.get().getEmail(), eligibleStudent.getEmail()))
                    _studentOptional.get().setEmail(eligibleStudent.getEmail());
                if (eligibleStudent.getCardId() != null  && !Objects.equals(_studentOptional.get().getCardId(), eligibleStudent.getCardId()))
                    _studentOptional.get().setCardId(eligibleStudent.getCardId());
                if (eligibleStudent.getStudyYear() != null  && !Objects.equals(_studentOptional.get().getStudyYear(), eligibleStudent.getStudyYear()))
                    _studentOptional.get().setStudyYear(eligibleStudent.getStudyYear());
                if (eligibleStudent.getFaculty() != null  && !Objects.equals(_studentOptional.get().getFaculty(), eligibleStudent.getFaculty()))
                    _studentOptional.get().setFaculty(eligibleStudent.getFaculty());
                if (eligibleStudent.getDepartment() != null  && !Objects.equals(_studentOptional.get().getDepartment(), eligibleStudent.getDepartment()))
                    _studentOptional.get().setDepartment(eligibleStudent.getDepartment());
                if (eligibleStudent.getCurriculum() != null  && !Objects.equals(_studentOptional.get().getCurriculum(), eligibleStudent.getCurriculum()))
                    _studentOptional.get().setCurriculum(eligibleStudent.getCurriculum());
                if (eligibleStudent.getTitle() != null  && !Objects.equals(_studentOptional.get().getTitle(), eligibleStudent.getTitle()))
                    _studentOptional.get().setTitle(eligibleStudent.getTitle());
                if (eligibleStudent.getAccessGranted() != null  && !Objects.equals(_studentOptional.get().getAccessGranted(), eligibleStudent.getAccessGranted()))
                    _studentOptional.get().setAccessGranted(eligibleStudent.getAccessGranted());
                if (eligibleStudent.getIsActive() != null  && !Objects.equals(_studentOptional.get().getIsActive(), eligibleStudent.getIsActive()))
                    _studentOptional.get().setIsActive(eligibleStudent.getIsActive());
                // nullable dateTime fields
                if (!Objects.equals(_studentOptional.get().getAppliedMembership(), eligibleStudent.getAppliedMembership()))
                    _studentOptional.get().setAppliedMembership(eligibleStudent.getAppliedMembership());
                if (!Objects.equals(_studentOptional.get().getAgreedToPay(), eligibleStudent.getAgreedToPay()))
                    _studentOptional.get().setAgreedToPay(eligibleStudent.getAgreedToPay());
                if (!Objects.equals(_studentOptional.get().getSiteVisited(), eligibleStudent.getSiteVisited()))
                    _studentOptional.get().setSiteVisited(eligibleStudent.getSiteVisited());
                if (!Objects.equals(_studentOptional.get().getPassedQuiz(), eligibleStudent.getPassedQuiz()))
                    _studentOptional.get().setPassedQuiz(eligibleStudent.getPassedQuiz());
                if (!Objects.equals(_studentOptional.get().getSentToFeo(), eligibleStudent.getSentToFeo()))
                    _studentOptional.get().setSentToFeo(eligibleStudent.getSentToFeo());
                if (!Objects.equals(_studentOptional.get().getDepositPaid(), eligibleStudent.getDepositPaid()))
                    _studentOptional.get().setDepositPaid(eligibleStudent.getDepositPaid());
                if (!Objects.equals(_studentOptional.get().getConfirmedMember(), eligibleStudent.getConfirmedMember()))
                    _studentOptional.get().setConfirmedMember(eligibleStudent.getConfirmedMember());

                eligibleStudentRepository.save(_studentOptional.get());
                return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_SUCCESS);
            }

            eligibleStudentRepository.save(eligibleStudent);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.SUCCESS);
        } catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    @Transactional
    public ServerResponse deleteStudent(long id) {
        try {
            eligibleStudentRepository.deleteById(id);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_SUCCESS);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_FAILED);
        }
    }

    @Transactional
    public ServerResponse updateEligibleStudentList(List<EligibleStudent> eligibleStudentList) {
        try {
            List<EligibleStudent> nonExistingEligibleStdList = new ArrayList<>();
            eligibleStudentList.forEach(eligibleStudent -> {
                if (eligibleStudent.getHkuId() != null) {
                    Optional<EligibleStudent> _studentOptional = eligibleStudentRepository.findEligibleStudentByHkuId(eligibleStudent.getHkuId());
                    if (_studentOptional.isPresent()) {
                        updateEligibleStudent(_studentOptional.get().getId(), eligibleStudent);
                    }
                    else {
                        eligibleStudentRepository.save(eligibleStudent);
                    }

                }
                else if (eligibleStudent.getEmail() != null) {
                    String str = eligibleStudent.getEmail();
                    Optional<List<EligibleStudent>> _studentList = eligibleStudentRepository.findEligibleStudentByEmailStartingWith(str.substring(0, str.indexOf("@")));
                    if(_studentList.isPresent() && !_studentList.get().isEmpty()) {
                        _studentList.get().forEach(_std -> {
                            if (_std.getName().equals(eligibleStudent.getName()))
                                updateEligibleStudent(_std.getId(), eligibleStudent);
                        });
                    }
                    else {
                        log.info(eligibleStudent.toString());
                        nonExistingEligibleStdList.add(eligibleStudent);
                        createEligibleStudent(eligibleStudent);
                    }
                }
                else {
                    nonExistingEligibleStdList.add(eligibleStudent);
                }
            });
            return ServerResponse.getInstance().responseEnum(ResponseEnum.SUCCESS).data("nonexistingEligibleStdList", nonExistingEligibleStdList);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.FAILED);
        }



    }
}
