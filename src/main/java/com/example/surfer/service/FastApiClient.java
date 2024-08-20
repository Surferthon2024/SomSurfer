package com.example.surfer.service;

import com.example.surfer.dto.CrawlingRequestDto;
import com.example.surfer.dto.ModelRequestDto;
import com.example.surfer.dto.ModelResponseDto;
import com.example.surfer.dto.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class FastApiClient {
    private final RestTemplate restTemplate = new RestTemplate();

    private List<String> crawlingUriList = Arrays.asList(
            "http://localhost:8000/dongduk-notice/",
            "",
            "http://localhost:8000/kangnam-notice/",
            ""
    );

    private String scheduleModelUri = "http://localhost:8000/extract_events/";

    public List<PostDto> getPost(int index, String keyword, String startDate) {
        CrawlingRequestDto requestDto = new CrawlingRequestDto(keyword, startDate);

        HttpHeaders header = new HttpHeaders();
        HttpEntity<?> httpEntity = new HttpEntity<>(requestDto, header);

        final ResponseEntity<List<PostDto>> response = restTemplate.exchange(crawlingUriList.get(index), HttpMethod.POST, httpEntity, new ParameterizedTypeReference<List<PostDto>>() {});
        return response.getBody();
    }

    public String getSchedule(String text) {

        ModelRequestDto requestDto = new ModelRequestDto(text);

        HttpHeaders header = new HttpHeaders();
        HttpEntity<?> httpEntity = new HttpEntity<>(requestDto, header);

//        final ResponseEntity<ModelResponseDto> response = restTemplate.exchange(scheduleModelUri, HttpMethod.POST, httpEntity, ModelResponseDto.class);
        final ResponseEntity<String> response = restTemplate.exchange(scheduleModelUri, HttpMethod.POST, httpEntity, String.class);
        log.info(String.valueOf(response.getBody()));

        return response.getBody();
//        return (List<String>) response.getBody().getEvents();
    }
}
