package com.example.surfer.controller;

import com.example.surfer.dto.NotificationDto;
import com.example.surfer.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationDto>> getNotifications(String url, String keyword){
        List<NotificationDto> schedules = notificationService.getNotifications(url+":"+keyword);

        return ResponseEntity.ok(schedules);
    }

    @DeleteMapping("/delete-keyword")
    public void deleteKeyword(String key){
        notificationService.deleteKeyword(key);
    }
}
