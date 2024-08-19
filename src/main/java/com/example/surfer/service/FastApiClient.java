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

import java.util.List;

@Component
public class FastApiClient {
    private final RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8000/dongduk-notice/";

    public List<PostDto> getPost(String keyword, String startDate) {
        CrawlingRequestDto requestDto = new CrawlingRequestDto(keyword, startDate);

        HttpHeaders header = new HttpHeaders();
        HttpEntity<?> httpEntity = new HttpEntity<>(requestDto, header);

        final ResponseEntity<List<PostDto>> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<List<PostDto>>() {});
        return response.getBody();
    }
}
