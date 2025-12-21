package com.spring.project.SpringSecurity.services;


import com.spring.project.SpringSecurity.dto.PostDTO;
import com.spring.project.SpringSecurity.entities.PostEntity;
import com.spring.project.SpringSecurity.exception.ResourceNotfoundException;
import com.spring.project.SpringSecurity.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImplementation implements PostService{
    @Override
    public PostDTO updatePost(PostDTO postDTO, Long id) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(()->new ResourceNotfoundException("Post Not found with id : "+id));

        postDTO.setId(id);
        modelMapper.map(postDTO,postEntity);
        PostEntity savedEntity = postRepository.save(postEntity);
        return modelMapper.map(savedEntity,PostDTO.class);
    }

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<PostDTO> getAllPost() {
        return postRepository.findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity,PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO createNewPost(PostDTO postDTO) {
        PostEntity postEntity = modelMapper.map(postDTO,PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity),PostDTO.class);
    }
    @Override
    public  PostDTO getPostbyId(Long id) {
         PostEntity postEntity= postRepository.findById(id).
                 orElseThrow(()->new ResourceNotfoundException("Post Not found with id : "+id));
         return modelMapper.map(postEntity,PostDTO.class);
    }

}
