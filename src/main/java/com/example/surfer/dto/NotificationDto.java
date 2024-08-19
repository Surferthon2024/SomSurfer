package com.example.surfer.dto;

import java.time.LocalDate;

public class NotificationDto {
    private String title;
    private String content;
    private LocalDate uploadDate;
    private String url;

    public NotificationDto(){ }

    public NotificationDto(String title, String content, LocalDate uploadDate, String url) {
        this.title = title;
        this.content = content;
        this.uploadDate = uploadDate;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }
}
