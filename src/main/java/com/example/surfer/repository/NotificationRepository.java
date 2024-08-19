package com.example.surfer.repository;

import com.example.surfer.dto.NotificationDto;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@Repository
public class NotificationRepository {
    private Map<String, List<NotificationDto>>  notificationMap = new HashMap<>();

    public List<NotificationDto> getNotifications(String key) {
        return notificationMap.get(key);
    }

    public void addNotification(String key, String title, String content, LocalDate date, String url) {
        NotificationDto notificationDto = new NotificationDto(title, content, date, url);

        if(notificationMap.containsKey(key)){
            notificationMap.get(key).add(notificationDto);
        }else{
            List<NotificationDto> list = new ArrayList<>();
            list.add(notificationDto);
            notificationMap.put(key, list);
        }
    }

    public void deleteKeyword(String key){
        notificationMap.remove(key);
    }
}
