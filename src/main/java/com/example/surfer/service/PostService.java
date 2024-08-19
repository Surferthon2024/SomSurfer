package com.example.surfer.service;

import com.example.surfer.dto.PostDto;
import com.example.surfer.exception.CustomException;
import com.example.surfer.exception.ErrorCode;
import com.example.surfer.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    @Autowired
    private final FastApiClient fastApiClient;

    @Autowired
    private final KeywordRepository keywordRepository;

    public List<PostDto> getPostList(String site, String keyword) {
        // 키워드 생성 시간은 DB가 없는 관계로 하드 코딩, 추후 수정
        String startDate = "2024-06-01";

        if(site.equals(keywordRepository.getSites().get(0))) {
            return fastApiClient.getPost(0, keyword, startDate);
        } if (site.equals(keywordRepository.getSites().get(2))) {
            return fastApiClient.getPost(2, keyword, startDate);
        } else {
            throw new CustomException(ErrorCode.INVALID_FORM_DATA);
        }

    }



}
