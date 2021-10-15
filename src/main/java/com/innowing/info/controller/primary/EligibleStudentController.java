package com.innowing.info.controller.primary;

import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.primary.EligibleStudent;
import com.innowing.info.model.EligibleStudentPage;
import com.innowing.info.model.EligibleStudentSearchCriteria;
import com.innowing.info.service.primary.EligibleStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ Author     ：Edison
 * @ Date       ：Created in 15:13 2021/5/25
 * @ Description：Engg Staff Controller
 * @ Modified By：
 * @Version: 1.0
 */
@Controller
@RequestMapping(path = "eligibleStudents")
public class EligibleStudentController {
    private final EligibleStudentService eligibleStudentService;
    @Autowired
    public EligibleStudentController(EligibleStudentService eligibleStudentService) {
        this.eligibleStudentService = eligibleStudentService;
    }

    @PostMapping
    @ResponseBody
    ServerResponse createEligibleStudent(@RequestBody EligibleStudent eligibleStudent){
        return eligibleStudentService.createEligibleStudent(eligibleStudent);
    }

    @PostMapping("setAllStudentInactive")
    @ResponseBody
    ServerResponse setAllStudentInactive() { return eligibleStudentService.setAllStudentInactive();}


    @GetMapping
    @ResponseBody
    ServerResponse getEligibleStudents(EligibleStudentPage eligibleStudentPage,
                                       EligibleStudentSearchCriteria eligibleStudentSearchCriteria) {
        return eligibleStudentService.getEligibleStudents(eligibleStudentPage, eligibleStudentSearchCriteria);
    }
//

    @GetMapping("{hkuId}")
    @ResponseBody
    public ServerResponse getEligibleStudentByHkuId(@PathVariable Long hkuId) {
        return eligibleStudentService.getEligibleStudentByHkuId(hkuId);
    }

    @GetMapping("id")
    @ResponseBody
    public ServerResponse getEligibleStudentById(@RequestParam Long id) {
        return eligibleStudentService.getEligibleStudentById(id);
    }
//
    @PatchMapping("{id}")
    @ResponseBody
    ServerResponse updateEligibleStudent(@PathVariable Long id, @RequestBody EligibleStudent eligibleStudent){
        return eligibleStudentService.updateEligibleStudent(id, eligibleStudent);
    }

    @PatchMapping("list")
    @ResponseBody
    ServerResponse updateEligibleStudentList(@RequestBody List<EligibleStudent> eligibleStudentList){
        return eligibleStudentService.updateEligibleStudentList(eligibleStudentList);
    }

//    @DeleteMapping("delete")
//    @ResponseBody
//    public ServerResponse deleteEligibleStudent(@RequestParam long hkuId) {
//        return eligibleStudentService.deleteStudent(hkuId);
//    }
    @DeleteMapping("{id}")
    @ResponseBody
    public ServerResponse deleteEligibleStudent(@PathVariable long id) {
        return eligibleStudentService.deleteStudent(id);
    }


}
