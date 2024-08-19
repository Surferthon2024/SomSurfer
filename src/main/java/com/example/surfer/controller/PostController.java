package com.example.surfer.controller;

import com.example.surfer.dto.PostDto;
import com.example.surfer.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private final PostService postService;

    @GetMapping("/{site}/{keyword}")
    public ResponseEntity<List<PostDto>> getPost(@PathVariable String site, @PathVariable String keyword) {
        List<PostDto> dtoList = postService.getPostList(site, keyword);
        return ResponseEntity.ok(dtoList);
    }



}
