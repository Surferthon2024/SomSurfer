package com.example.surfer.service;

import com.example.surfer.dto.KeywordDto;
import com.example.surfer.repository.KeyRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class KeyService {

    private static KeyService keyService;

    private final KeyRepository keyRepository = KeyRepository.getInstance();

    private KeyService() {}

    public static synchronized KeyService getInstance() {
        if (keyService == null) {
            keyService = new KeyService();
        }
        return keyService;
    }

    public List<String> getSites() {
        return keyRepository.getSites();
    }

    public void addKeyword(List<String> sites, String keyword, LocalDate date) {
        if (sites != null && !sites.isEmpty() && keyword != null && !keyword.isEmpty()) {
            keyRepository.addKeyword(new KeywordDto(sites, keyword, date));
        }
    }

    public void removeKeyword(UUID id) {
        keyRepository.removeKeyword(id);
    }

    public List<KeywordDto> getAllKeywords() {
        return keyRepository.getAllKeywords();
    }

    public Optional<KeywordDto> getKeywordById(UUID id) {
        return keyRepository.getKeywordById(id);
    }
}
