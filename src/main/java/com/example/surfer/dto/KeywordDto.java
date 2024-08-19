package com.example.surfer.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class KeywordDto {

    private UUID id;
    private String site;
    private String keyword;
    private LocalDate date;

    public KeywordDto(String site, String keyword, LocalDate date) {
        this.id = UUID.randomUUID(); // 고유 식별자 생성
        this.site = site;
        this.keyword = keyword;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public String getSite() {
        return site;
    }

    public String getKeyword() {
        return keyword;
    }


    public LocalDate getDate() {
        return date;
    }

}