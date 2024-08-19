package com.example.surfer.service;

import com.example.surfer.dto.CrawlingRequestDto;
import com.example.surfer.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Arrays;
import java.util.List;

@Component
public class FastApiClient {
    private final RestTemplate restTemplate = new RestTemplate();

    private List<String> crawlingUriList = Arrays.asList(
            "http://localhost:8000/dongduk-notice",
            "",
            "http://localhost:8000/kangnam-notice",
            ""
    );

    private String scheduleModelUri = "http://localhost:8000/extract_events";

    public List<PostDto> getPost(int index, String keyword, String startDate) {
        CrawlingRequestDto requestDto = new CrawlingRequestDto(keyword, startDate);

        HttpHeaders header = new HttpHeaders();
        HttpEntity<?> httpEntity = new HttpEntity<>(requestDto, header);

        final ResponseEntity<List<PostDto>> response = restTemplate.exchange(crawlingUriList.get(index), HttpMethod.POST, httpEntity, new ParameterizedTypeReference<List<PostDto>>() {});
        return response.getBody();
    }

    public List<String> getSchedule(String text) {

        HttpHeaders header = new HttpHeaders();
        HttpEntity<?> httpEntity = new HttpEntity<>(text, header);

        final ResponseEntity<List<String>> response = restTemplate.exchange(scheduleModelUri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<List<String>>() {});
        return response.getBody();
    }
}
