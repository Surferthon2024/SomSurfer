package com.example.surfer.repository;

import com.example.surfer.dto.ScheduleDto;
import com.example.surfer.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleRepository {
    @Autowired
    private ScheduleService scheduleService;

    public ScheduleDto createSchedule(String title, String startD, String endD) {
        ScheduleDto scheduleDto = new ScheduleDto(title, startD, endD);
        scheduleService.getSchedules().add(scheduleDto);

        return scheduleDto;
    }

    public void deleteSchedule(int index) {
        scheduleService.getSchedules().remove(index);
    }
}
