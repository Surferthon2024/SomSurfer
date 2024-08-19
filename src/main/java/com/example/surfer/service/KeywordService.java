package com.example.surfer.service;

import com.example.surfer.dto.KeywordDto;
import com.example.surfer.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class KeywordService {

    @Autowired
    private final KeywordRepository keywordRepository;

    public List<String> getSites() {
        return keywordRepository.getSites();
    }

    public void addKeyword(List<String> sites, String keyword, LocalDate date) {
        if (sites != null && !sites.isEmpty() && keyword != null && !keyword.isEmpty()) {
            keywordRepository.addKeyword(new KeywordDto(sites, keyword, date));
        }
    }

    public void removeKeyword(UUID id) {
        keywordRepository.removeKeyword(id);
    }

    public List<KeywordDto> getAllKeywords() {
        return keywordRepository.getAllKeywords();
    }

    public Optional<KeywordDto> getKeywordById(UUID id) {
        return keywordRepository.getKeywordById(id);
    }
}
