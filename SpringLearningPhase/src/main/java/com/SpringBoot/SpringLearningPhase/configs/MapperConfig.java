package com.SpringBoot.SpringLearningPhase.configs;

import com.SpringBoot.SpringLearningPhase.dto.EmployeeDTO;
import com.SpringBoot.SpringLearningPhase.entities.EmployeeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper getModelMapper(){
        ModelMapper mapper =  new ModelMapper();
        return mapper;
    }
}
