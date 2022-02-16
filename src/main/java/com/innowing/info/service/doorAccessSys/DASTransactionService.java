package com.innowing.info.service.doorAccessSys;

import com.innowing.info.common.ResponseEnum;
import com.innowing.info.common.ServerResponse;
import com.innowing.info.entity.doorAccessSys.DASTransaction;
import com.innowing.info.entity.primary.EligibleStudent;
import com.innowing.info.entity.primary.project.Project;
import com.innowing.info.entity.primary.project.ProjectStudent;
import com.innowing.info.repository.doorAccessSys.DASTransactionRepository;
import com.innowing.info.repository.primary.project.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DASTransactionService {
    private final DASTransactionRepository dasTransactionRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public DASTransactionService(DASTransactionRepository dasTransactionRepository, ProjectRepository projectRepository) {
        this.dasTransactionRepository = dasTransactionRepository;
        this.projectRepository = projectRepository;
    }


    public ServerResponse getDASTransactionByDate(int minusDays) {
        LocalDate today = LocalDate.now();
        LocalDate selectedDay = today.minusDays(minusDays);
        LocalDateTime startOfDay = LocalDateTime.of(selectedDay, LocalTime.MIDNIGHT);
        LocalDateTime endOfDay = LocalDateTime.of(selectedDay, LocalTime.MAX);
        List<DASTransaction> transactions = dasTransactionRepository.findAllByTransactionDateTimeBetween(startOfDay, endOfDay);
        return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data("transactions", transactions);
    }


    public ServerResponse getDASTransactionByCardId(String cardId) {
        try {
            List<DASTransaction> transactions = dasTransactionRepository.findAllByCardId(cardId);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data("transactions", transactions);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return ServerResponse.getInstance().responseEnum(ResponseEnum.FAILED);
    }

    public ServerResponse getDASTransactionCount(long projectId, List<String> doorIdList, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        try {
            Optional<Project> projectOptional = projectRepository.findById(projectId);
            if (projectOptional.isPresent()) {
                Project project = projectOptional.get();
                List<String> cardIdList = new ArrayList<>();
                for (ProjectStudent projectStudent : project.getProjectStudents()) {
                    // log.info(projectStudent.getEligibleStudent().getCardId());
                    cardIdList.add(projectStudent.getEligibleStudent().getCardId());
                }
                List<Integer> intDoorIdList= new ArrayList<>();
                for(String s : doorIdList) {
                    intDoorIdList.add(Integer.valueOf(s));
                }
                List<Object[]> testCount = dasTransactionRepository.getTransactionCountByDay(cardIdList, intDoorIdList, startDateTime, endDateTime);
                return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data("transactions", testCount);
            } else {
                return ServerResponse.getInstance().responseEnum(ResponseEnum.INVALID_PARAM);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return ServerResponse.getInstance().responseEnum(ResponseEnum.FAILED);
    }
}
