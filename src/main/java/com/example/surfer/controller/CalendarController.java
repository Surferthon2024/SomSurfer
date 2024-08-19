package com.example.surfer.controller;

import com.example.surfer.dto.ScheduleDto;
import com.example.surfer.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class CalendarController {
    @Autowired
    private ScheduleService scheduleService;

    //데이터 읽어오기
    @GetMapping
    public ResponseEntity<List<ScheduleDto>> getSchedule(){
        List<ScheduleDto> schedules = scheduleService.getSchedules();

        return ResponseEntity.ok(schedules);
    }
    //일정 추가
    @PostMapping("/add-schedule")
    public ResponseEntity<List<ScheduleDto>> createSchedule(@RequestBody String text){
        List<ScheduleDto> schedules = scheduleService.createSchedules(text);
        return ResponseEntity.ok(schedules);
    }

    //일정 삭제
    @DeleteMapping("/delete-schedule")
    public ResponseEntity<Boolean> deleteSchedule(int index){
        Boolean result = scheduleService.deleteSchedule(index);
        return ResponseEntity.ok(result);
    }
}
