package com.innowing.info.service;

import com.innowing.info.common.ResponseEnum;
import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.Student;
import com.innowing.info.entity.User;
import com.innowing.info.model.StudentPage;
import com.innowing.info.model.StudentSearchCriteria;
import com.innowing.info.repository.StudentCriteriaRepository;
import com.innowing.info.repository.StudentRepository;
import com.innowing.info.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @ Author     ：Edison
 * @ Date       ：Created in 11:45 2021/5/20
 * @ Description：Student Service type
 * @ Modified By：
 * @Version: 1.0
 */

@Service
@Slf4j
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentCriteriaRepository studentCriteriaRepository;

    public ServerResponse createStudent(Student student) {
        Optional<Student> studentOptional =  studentRepository.findStudentByHkuId(student.getHkuId());
        if (studentOptional.isPresent())
            return ServerResponse.getInstance().responseEnum(ResponseEnum.USERNAME_EXIST);

        try {
            studentRepository.save(student);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance()
                    .responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    public ServerResponse getStudents(StudentPage studentPage,
                                    StudentSearchCriteria studentSearchCriteria) {
        // front end return 1 -> backend use 0
        int currentPageNumber = studentPage.getPageNumber();
        if (currentPageNumber > 0)  studentPage.setPageNumber(currentPageNumber - 1);
        Page<Student> studentPage1 = studentCriteriaRepository.findAllWithFilters(studentPage, studentSearchCriteria);
        return ServerResponse.getInstance().responseEnum(ResponseEnum.SUCCESS).data("pageObject", studentPage1);
    }

    public ServerResponse deleteStudent(long hkuId) {
        try {
            studentRepository.deleteByHkuId(hkuId);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_SUCCESS);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_FAILED);
        }
    }

    public ServerResponse updateStudent(Student student) {
        try {
            Long id = student.getId();
            Student _student = studentRepository.getOne(id);

            _student.setHkuId(student.getHkuId());
            _student.setName(student.getName());
            _student.setEmail(student.getEmail());
            _student.setCardId(student.getCardId());
            _student.setStudyYear(student.getStudyYear());
            _student.setFaculty(student.getFaculty());
            _student.setDepartment(student.getDepartment());
            _student.setCurriculum(student.getCurriculum());
            _student.setTitle(student.getTitle());
            _student.setMemberStatus(student.getMemberStatus());

            studentRepository.save(_student);

            return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

//    public Page<Student> getStudent(StudentPage studentPage,
//                                    StudentSearchCriteria studentSearchCriteria) {
//        return studentCriteriaRepository.findAllWithFilters(studentPage, studentSearchCriteria);
//    }

}
