package com.example.surfer.service;

import com.example.surfer.dto.ScheduleDto;
import com.example.surfer.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired private ScheduleRepository scheduleRepository;

    private static final ScheduleService instance = new ScheduleService();
    private List<ScheduleDto> schedules = new ArrayList<>();

    public ScheduleService getInstance() {
        return instance;
    }

    public List<ScheduleDto> getSchedules() {
        return schedules;
    }

    public List<ScheduleDto> createSchedules(int index){
        List<String> plans = new ArrayList<>();
        //index로 본문을 알아내서 기간추출 api 호출 -> 추출결과 plans에 저장

        for(String plan : plans){
            String[] str = plan.split("|");

            ScheduleDto scheduleDto = null;
            if(str.length > 1)
                scheduleDto = scheduleRepository.createSchedule(str[0], str[1], str[2]);
            else
                scheduleDto = scheduleRepository.createSchedule(str[0], str[1], str[1]);
        }

        return schedules;
    }

    public String deleteSchedule(int index){
        try{
            scheduleRepository.deleteSchedule(index);
            return "Remove Success";
        }catch (Exception e){
            return "Remove failed";
        }

    }
}
