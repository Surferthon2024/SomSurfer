package com.example.surfer.repository;

import com.example.surfer.dto.KeywordDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class KeyRepository {

    private static KeyRepository instance;
    private final List<KeywordDto> keywords = new ArrayList<>();
    private final List<String> sites = Arrays.asList(
            "동덕 공지사항",
            "한국 장학재단",
            "사이트3",
            "사이트4"
    );

    private KeyRepository() {}

    public static synchronized KeyRepository getInstance() {
        if (instance == null) {
            instance = new KeyRepository();
        }
        return instance;
    }

    public List<String> getSites() {
        return sites;
    }

    public void addKeyword(KeywordDto keyword) {
        keywords.add(keyword);
    }

    public void removeKeyword(UUID id) {
        keywords.removeIf(keyword -> keyword.getId().equals(id));
    }

    public List<KeywordDto> getAllKeywords() {
        return new ArrayList<>(keywords);
    }

    public Optional<KeywordDto> getKeywordById(UUID id) {
        return keywords.stream().filter(keyword -> keyword.getId().equals(id)).findFirst();
    }
}