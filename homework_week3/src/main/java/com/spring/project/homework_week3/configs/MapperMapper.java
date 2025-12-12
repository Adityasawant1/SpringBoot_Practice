package com.spring.project.homework_week3.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperMapper {

    @Bean
    public ModelMapper getModelMapper(){
        ModelMapper mapper =  new ModelMapper();
        return mapper;
    }
}
