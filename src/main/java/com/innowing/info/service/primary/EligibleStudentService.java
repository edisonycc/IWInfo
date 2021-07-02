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

    @Transactional
    public ServerResponse updateEligibleStudent(Long id, EligibleStudent eligibleStudent) {
        try {
//            Long id = eligibleStudent.getId();
            Optional<EligibleStudent> _student = eligibleStudentRepository.findById(id);

            if(_student.isPresent()) {
                if (eligibleStudent.getHkuId() != null  && !Objects.equals(_student.get().getHkuId(), eligibleStudent.getHkuId()))
                    _student.get().setHkuId(eligibleStudent.getHkuId());
                if (eligibleStudent.getName() != null  && !Objects.equals(_student.get().getName(), eligibleStudent.getName()))
                    _student.get().setName(eligibleStudent.getName());
                if (eligibleStudent.getEmail() != null  && !Objects.equals(_student.get().getEmail(), eligibleStudent.getEmail()))
                    _student.get().setEmail(eligibleStudent.getEmail());
                if (eligibleStudent.getCardId() != null  && !Objects.equals(_student.get().getCardId(), eligibleStudent.getCardId()))
                    _student.get().setCardId(eligibleStudent.getCardId());
                if (eligibleStudent.getStudyYear() != null  && !Objects.equals(_student.get().getStudyYear(), eligibleStudent.getStudyYear()))
                    _student.get().setStudyYear(eligibleStudent.getStudyYear());
                if (eligibleStudent.getFaculty() != null  && !Objects.equals(_student.get().getFaculty(), eligibleStudent.getFaculty()))
                    _student.get().setFaculty(eligibleStudent.getFaculty());
                if (eligibleStudent.getDepartment() != null  && !Objects.equals(_student.get().getDepartment(), eligibleStudent.getDepartment()))
                    _student.get().setDepartment(eligibleStudent.getDepartment());
                if (eligibleStudent.getCurriculum() != null  && !Objects.equals(_student.get().getCurriculum(), eligibleStudent.getCurriculum()))
                    _student.get().setCurriculum(eligibleStudent.getCurriculum());
                if (eligibleStudent.getTitle() != null  && !Objects.equals(_student.get().getTitle(), eligibleStudent.getTitle()))
                    _student.get().setTitle(eligibleStudent.getTitle());
                if (eligibleStudent.getAccessGranted() != null  && !Objects.equals(_student.get().getAccessGranted(), eligibleStudent.getAccessGranted()))
                    _student.get().setAccessGranted(eligibleStudent.getAccessGranted());
                if (eligibleStudent.getIsActive() != null  && !Objects.equals(_student.get().getIsActive(), eligibleStudent.getIsActive()))
                    _student.get().setIsActive(eligibleStudent.getIsActive());
                // nullable dateTime fields
                if (!Objects.equals(_student.get().getAppliedMembership(), eligibleStudent.getAppliedMembership()))
                    _student.get().setAppliedMembership(eligibleStudent.getAppliedMembership());
                if (!Objects.equals(_student.get().getAgreedToPay(), eligibleStudent.getAgreedToPay()))
                    _student.get().setAgreedToPay(eligibleStudent.getAgreedToPay());
                if (!Objects.equals(_student.get().getSiteVisited(), eligibleStudent.getSiteVisited()))
                    _student.get().setSiteVisited(eligibleStudent.getSiteVisited());
                if (!Objects.equals(_student.get().getPassedQuiz(), eligibleStudent.getPassedQuiz()))
                    _student.get().setPassedQuiz(eligibleStudent.getPassedQuiz());
                if (!Objects.equals(_student.get().getSentToFeo(), eligibleStudent.getSentToFeo()))
                    _student.get().setSentToFeo(eligibleStudent.getSentToFeo());
                if (!Objects.equals(_student.get().getDepositPaid(), eligibleStudent.getDepositPaid()))
                    _student.get().setDepositPaid(eligibleStudent.getDepositPaid());
                if (!Objects.equals(_student.get().getConfirmedMember(), eligibleStudent.getConfirmedMember()))
                    _student.get().setConfirmedMember(eligibleStudent.getConfirmedMember());

                eligibleStudentRepository.save(_student.get());
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
            List<EligibleStudent> nonexistingElighbleStdList = new ArrayList<>();
            eligibleStudentList.forEach(eligibleStudent -> {
                if (eligibleStudent.getHkuId() != null) {
                    Optional<EligibleStudent> _student = eligibleStudentRepository.findEligibleStudentByHkuId(eligibleStudent.getHkuId());
                    if (_student.isPresent()) {
                        updateEligibleStudent(_student.get().getId(), eligibleStudent);
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
                        nonexistingElighbleStdList.add(eligibleStudent);
                        createEligibleStudent(eligibleStudent);
                    }
                }
                else {
                    nonexistingElighbleStdList.add(eligibleStudent);
                }
            });
            return ServerResponse.getInstance().responseEnum(ResponseEnum.SUCCESS).data("nonexistingEligibleStdList", nonexistingElighbleStdList);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.FAILED);
        }



    }
}
