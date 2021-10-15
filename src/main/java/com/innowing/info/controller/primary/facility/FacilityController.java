package com.innowing.info.controller.primary.facility;

import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.primary.facility.Facility;
import com.innowing.info.service.primary.facility.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ Author     ：Edison
 * @ Date       ：Created in 12:13 2021/7/29
 * @ Description：Project Controller
 * @ Modified By：
 * @Version: 1.0
 */
@Controller
@RequestMapping(path = "facility")
public class FacilityController {
    private final FacilityService facilityService;
    @Autowired
    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @PostMapping
    @ResponseBody
    ServerResponse createFacility(@RequestBody Facility facility) {
        return facilityService.createFacility(facility);
    }

    @GetMapping
    @ResponseBody
    ServerResponse getFacilityList() { return facilityService.getFacilityList(); }

}
