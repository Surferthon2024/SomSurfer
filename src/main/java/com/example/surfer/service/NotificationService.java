package com.example.surfer.service;

import com.example.surfer.dto.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.surfer.repository.NotificationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    @Autowired private NotificationRepository notificationRepository;

    public List<NotificationDto> getNotifications(String key) {

        List<String> notificationDataList = new ArrayList<>();
        //url, keyword에 맞게 크롤링 호출 -> notificationDataList에 저장

        for(String data : notificationDataList){
            String[] dataArr = data.split("\\|");
            LocalDate date = LocalDate.parse(dataArr[2]);
            notificationRepository.addNotification(key, dataArr[0],dataArr[1],date,dataArr[3]);
        }
        return notificationRepository.getNotifications(key);
    }

    public void deleteKeyword(String key){
        notificationRepository.deleteKeyword(key);
    }
}
