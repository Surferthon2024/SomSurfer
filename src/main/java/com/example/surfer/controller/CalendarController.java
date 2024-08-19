package com.example.surfer.controller;

import com.example.surfer.service.GoogleCalendarService;
import com.google.api.services.calendar.model.Event;
import com.example.surfer.dto.ScheduleDto;
import com.example.surfer.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {
    @Autowired private ScheduleService scheduleService;
    @Autowired private GoogleCalendarService googleCalendarService;
    //데이터 읽어오기
    @GetMapping
    public ResponseEntity<List<ScheduleDto>> getSchedule(){
        List<ScheduleDto> schedules = scheduleService.getSchedules();

        return ResponseEntity.ok(schedules);
    }
    //일정 추가
    @PostMapping("/add-schedule")
    public ResponseEntity<List<ScheduleDto>> createSchedule(int index){
        List<ScheduleDto> schedules = scheduleService.createSchedules(index);
        return ResponseEntity.ok(schedules);
    }

    //일정 삭제
    @DeleteMapping("/delete-schedule")
    public ResponseEntity<Boolean> deleteSchedule(int index){
        Boolean result = scheduleService.deleteSchedule(index);
        return ResponseEntity.ok(result);
    }

    //구글 캘린더에 일정 추가
    @PostMapping("/add-googlecalendar")
    public ResponseEntity<String> addEvent(@RequestBody Event event) {
        try {
            googleCalendarService.addEvent(event);
            return ResponseEntity.ok("Event added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to add event: " + e.getMessage());
        }
    }
}
