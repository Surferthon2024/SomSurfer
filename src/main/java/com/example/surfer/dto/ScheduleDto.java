package com.example.surfer.dto;

import java.util.Date;
import java.util.List;

public class ScheduleDto {
    private String title;
    private String startDate; //String?date?
    private String endDate;

    public ScheduleDto(){ }
    public ScheduleDto(String title, String startDate, String endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
