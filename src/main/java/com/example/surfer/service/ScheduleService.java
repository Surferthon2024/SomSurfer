package com.example.surfer.service;

import com.example.surfer.dto.ScheduleDto;
import com.example.surfer.exception.CustomException;
import com.example.surfer.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.example.surfer.exception.ErrorCode.INVALID_FORM_DATA;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private final FastApiClient fastApiClient;

    public List<ScheduleDto> getSchedules(){
        return scheduleRepository.getSchedules();
    }

    public List<ScheduleDto> createSchedules(String text){

        List<String> plans = fastApiClient.getSchedule(text);

        List<ScheduleDto> result = new ArrayList<>();
        for(String plan : plans){
            String[] str = plan.split("\\|");

            ScheduleDto scheduleDto = null;
            if(str.length > 1) {
                LocalDate startD = LocalDate.parse(str[1], DateTimeFormatter.ISO_DATE);
                LocalDate endD = LocalDate.parse(str[2], DateTimeFormatter.ISO_DATE);
                scheduleDto = scheduleRepository.createSchedule(str[0], startD, endD);
            }else {
                LocalDate startD = LocalDate.parse(str[1], DateTimeFormatter.ISO_DATE);
                scheduleDto = scheduleRepository.createSchedule(str[0], startD, startD);
            }
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
