package com.example.surfer.Controller;

import com.example.surfer.dto.ScheduleDto;
import com.example.surfer.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/schedule")
public class CalendarController {
    @Autowired private ScheduleService scheduleService;

    //데이터 읽어오기
    @GetMapping
    public ResponseEntity<List<ScheduleDto>> getSchedule(){
        List<ScheduleDto> schedules = scheduleService.getSchedules();

        return ResponseEntity.ok(schedules);
    }
    //일정 추가
    @PostMapping("/create")
    public ResponseEntity<List<ScheduleDto>> createSchedule(int index){
        List<ScheduleDto> schedules = scheduleService.createSchedules(index);
        return ResponseEntity.ok(schedules);
    }

    //일정 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteSchedule(int index){
        String result = scheduleService.deleteSchedule(index);

        return ResponseEntity.ok(result);
    }
}
