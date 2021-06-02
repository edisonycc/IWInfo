package com.innowing.info.controller;

import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.EligibleStudent;
import com.innowing.info.entity.Student;
import com.innowing.info.model.EligibleStudentPage;
import com.innowing.info.model.EligibleStudentSearchCriteria;
import com.innowing.info.model.StudentPage;
import com.innowing.info.model.StudentSearchCriteria;
import com.innowing.info.service.EligibleStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ Author     ：Edison
 * @ Date       ：Created in 15:13 2021/5/25
 * @ Description：Engg Staff Controller
 * @ Modified By：
 * @Version: 1.0
 */
@Controller
@RequestMapping(path = "eligibleStudent")
public class EligibleStudentController {
    private final EligibleStudentService eligibleStudentService;
    @Autowired
    public EligibleStudentController(EligibleStudentService eligibleStudentService) {
        this.eligibleStudentService = eligibleStudentService;
    }

    @PostMapping("create")
    @ResponseBody
    ServerResponse createEligibleStudent(@RequestBody EligibleStudent eligibleStudent){
        return eligibleStudentService.createEligibleStudent(eligibleStudent);
    }

    @GetMapping("list")
    @ResponseBody
    ServerResponse getEligibleStudents(EligibleStudentPage eligibleStudentPage,
                                       EligibleStudentSearchCriteria eligibleStudentSearchCriteria) {
        return eligibleStudentService.getEligibleStudents(eligibleStudentPage, eligibleStudentSearchCriteria);
    }
//

    @GetMapping
    @ResponseBody
    public ServerResponse getEligibleStudentByHkuId(@RequestParam Long hkuId) {
        return eligibleStudentService.getEligibleStudentByHkuId(hkuId);
    }
//
    @PostMapping("update")
    @ResponseBody
    ServerResponse updateEligibleStudent(@RequestBody EligibleStudent eligibleStudent){
        return eligibleStudentService.updateEligibleStudent(eligibleStudent);
    }

    @DeleteMapping(value = "delete")
    @ResponseBody
    public ServerResponse deleteEligibleStudent(@RequestParam long hkuId) {
        return eligibleStudentService.deleteStudent(hkuId);
    }


}
