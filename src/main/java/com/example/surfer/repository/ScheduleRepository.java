package com.example.surfer.repository;

import com.example.surfer.dto.ScheduleDto;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleRepository {
    private List<ScheduleDto> schedules = new ArrayList<>();

    public List<ScheduleDto> getSchedules(){
        return schedules;
    }

    public ScheduleDto createSchedule(String title, LocalDate startD, LocalDate endD) {
        ScheduleDto scheduleDto = new ScheduleDto(title, startD, endD);
        schedules.add(scheduleDto);

        return scheduleDto;
    }

    public void deleteSchedule(int index) {
        schedules.remove(index);
    }
}
