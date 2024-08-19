package com.example.surfer.repository;

import com.example.surfer.dto.KeywordDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
@Getter
public class KeywordRepository {

    private final List<KeywordDto> keywords = new ArrayList<>();
    private final List<String> sites = Arrays.asList(
            "동덕여대 컴퓨터학과",
            "한국 장학재단",
            "강남대학교",
            "링커리어"
    );



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