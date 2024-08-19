package com.example.surfer.service;

import ch.qos.logback.core.spi.ErrorCodes;
import com.example.surfer.dto.ScheduleDto;
import com.example.surfer.exception.CustomException;
import com.example.surfer.exception.ErrorCode;
import com.example.surfer.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.surfer.exception.ErrorCode.INVALID_FORM_DATA;

@Service
public class ScheduleService {
    @Autowired private ScheduleRepository scheduleRepository;

    public List<ScheduleDto> getSchedules(){
        return scheduleRepository.getSchedules();
    }

    public List<ScheduleDto> createSchedules(int index){
        List<String> plans = new ArrayList<>();
        //index로 본문을 알아내서 기간추출 api 호출 -> 추출결과 plans에 저장

        //test
        plans.add("수강신청|2024-08-24|2024-08-26");

        List<ScheduleDto> result = new ArrayList<>();
        for(String plan : plans){
            String[] str = plan.split("\\|");

            ScheduleDto scheduleDto = null;
            if(str.length > 1)
                scheduleDto = scheduleRepository.createSchedule(str[0], str[1], str[2]);
            else
                scheduleDto = scheduleRepository.createSchedule(str[0], str[1], str[1]);
            if(scheduleDto != null)
                result.add(scheduleDto);
        }

        return result;
    }

    public boolean deleteSchedule(int index){
        try{
            scheduleRepository.deleteSchedule(index);
            return true;
        }catch (Exception e){
            throw new CustomException(INVALID_FORM_DATA);
        }

    }
}
