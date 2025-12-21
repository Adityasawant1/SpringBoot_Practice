package com.spring.project.SpringSecurity.controllers;


import com.spring.project.SpringSecurity.dto.PostDTO;
import com.spring.project.SpringSecurity.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPost()
    {
        return ResponseEntity.ok(postService.getAllPost());
    }

    @GetMapping(path= "/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "postId")Long id)
    {
        return ResponseEntity.ok(postService.getPostbyId(id));
    }


    @PostMapping
    public ResponseEntity<PostDTO> createNewPost(@RequestBody PostDTO postDTO)
    {
        return new ResponseEntity(postService.createNewPost(postDTO), HttpStatus.CREATED);

    }

    @PutMapping(path = {"/{postId}"})
    public PostDTO updatePost(@RequestBody PostDTO postDTO,@PathVariable(name = "postId")Long id)
    {
        return postService.updatePost(postDTO,id);

    }
}
