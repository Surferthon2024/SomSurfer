package com.example.surfer.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class KeywordDto {

    private UUID id;
    private List<String> sites;
    private String keyword;
    private LocalDate date;

    public KeywordDto(List<String> sites, String keyword, LocalDate date) {
        this.id = UUID.randomUUID(); // 고유 식별자 생성
        this.sites = sites;
        this.keyword = keyword;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public List<String> getSites() {
        return sites;
    }

    public String getKeyword() {
        return keyword;
    }


    public LocalDate getDate() {
        return date;
    }

}