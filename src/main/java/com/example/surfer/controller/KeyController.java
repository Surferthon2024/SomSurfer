package com.example.surfer.controller;

import com.example.surfer.dto.KeywordDto;
import com.example.surfer.service.KeyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/keywords")
public class KeyController {

    private final KeyService keyService = KeyService.getInstance();

    @GetMapping("/sites")
    public ResponseEntity<List<String>> getSites() {
        List<String> sites = keyService.getSites();
        return ResponseEntity.ok(sites);
    }

    @GetMapping
    public ResponseEntity<List<KeywordDto>> getAllKeywords() {
        List<KeywordDto> keywords = keyService.getAllKeywords();
        return ResponseEntity.ok(keywords);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KeywordDto> getKeyword(@PathVariable("id") UUID id) {
        Optional<KeywordDto> keywordDto = keyService.getKeywordById(id);
        return keywordDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> addKeyword(@RequestBody Map<String, Object> requestBody) {
        List<Integer> siteIndex = (List<Integer>) requestBody.get("siteIndex");
        String keyword = (String) requestBody.get("keyword");
        String date = (String) requestBody.get("date");

        List<String> sites = keyService.getSites();
        List<String> selectedSites = siteIndex.stream()
                .map(index -> {
                    if (index >= 0 && index < sites.size()) {
                        return sites.get(index);
                    } else {
                        throw new IllegalArgumentException("Invalid site index: " + index);
                    }
                })
                .collect(Collectors.toList());

        LocalDate parsedDate = LocalDate.parse(date);
        keyService.addKeyword(selectedSites, keyword, parsedDate);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKeyword(@PathVariable("id") UUID id) {
        Optional<KeywordDto> keywordDto = keyService.getKeywordById(id);
        if (keywordDto.isPresent()) {
            keyService.removeKeyword(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}