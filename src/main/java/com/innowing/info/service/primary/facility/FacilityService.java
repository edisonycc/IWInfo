package com.innowing.info.service.primary.facility;

import com.innowing.info.common.ResponseEnum;
import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.primary.facility.Facility;
import com.innowing.info.repository.primary.facility.FacilityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FacilityService {
    @Autowired
    FacilityRepository facilityRepository;

    public ServerResponse createFacility(Facility facility) {
        try {
            facilityRepository.save(facility);
            return ServerResponse.
                    getInstance().
                    responseEnum(ResponseEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance()
                    .responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    public ServerResponse getFacilityList() {
        List<Facility> facilityList = facilityRepository.findAll();
        return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data("facilityList", facilityList);
    }
}
