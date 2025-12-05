package com.spring.week2.homework_week2.services;

import com.spring.week2.homework_week2.dto.DepartmentDTO;
import com.spring.week2.homework_week2.entities.DepartmentEntity;
import com.spring.week2.homework_week2.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {


    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository,ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper=modelMapper;
    }


    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        return departmentEntities
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity,DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<DepartmentDTO> getDeptById(Long id) {
        return departmentRepository.findById(id)
                .map(departmentEntity -> modelMapper.map(departmentEntity,DepartmentDTO.class));

    }

    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        DepartmentEntity departmentEntity = modelMapper.map(departmentDTO,DepartmentEntity.class);
         DepartmentEntity departmentEntity1=departmentRepository.save(departmentEntity);
         return modelMapper.map(departmentEntity1,DepartmentDTO.class);
    }

    public Boolean deleteDeptById(Long id) {
       boolean exist = departmentRepository.existsById(id);
       if(!exist) return false;

           departmentRepository.deleteById(id);
           return true;



    }

    public DepartmentDTO updateDept(Long id, DepartmentDTO departmentDTO) {
        DepartmentEntity departmentEntity = modelMapper.map(departmentDTO,DepartmentEntity.class);
        departmentEntity.setId(id);
        DepartmentEntity saveDeptEntity = departmentRepository.save(departmentEntity);
        return modelMapper.map(saveDeptEntity,DepartmentDTO.class);
    }
}
