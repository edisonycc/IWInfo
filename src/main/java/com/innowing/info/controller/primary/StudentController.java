package com.innowing.info.controller.primary;

import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.primary.Student;
import com.innowing.info.model.StudentPage;
import com.innowing.info.model.StudentSearchCriteria;
import com.innowing.info.service.primary.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "student")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
//    @Autowired
//    private RoleService roleService;
//    @Resource
//    private JwtTokenUtils jwtToken;

    /**
     * create student
     * @param student
     * @return
     */
    @PostMapping("create")
    @ResponseBody
    ServerResponse createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @GetMapping("list")
    @ResponseBody
    ServerResponse getStudents(StudentPage studentPage,
                              StudentSearchCriteria studentSearchCriteria) {
        return studentService.getStudents(studentPage, studentSearchCriteria);
    }

    // delete a std
    @DeleteMapping(value = "delete")
    @ResponseBody
    public ServerResponse deleteStudent(@RequestParam long hkuId) {
        return studentService.deleteStudent(hkuId);
    }

    @PostMapping("update")
    @ResponseBody
    ServerResponse updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }


}
