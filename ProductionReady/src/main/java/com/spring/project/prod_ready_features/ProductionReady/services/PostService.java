package com.spring.project.prod_ready_features.ProductionReady.services;

import com.spring.project.prod_ready_features.ProductionReady.dto.PostDTO;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {

    List<PostDTO> getAllPost();


    PostDTO createNewPost(PostDTO postDTO);

    @Nullable PostDTO getPostbyId(Long id);

    PostDTO updatePost(PostDTO postDTO, Long id);
}
