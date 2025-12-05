package com.SpringBoot.SpringLearningPhase.services;

import com.SpringBoot.SpringLearningPhase.dto.EmployeeDTO;
import com.SpringBoot.SpringLearningPhase.entities.EmployeeEntity;
import com.SpringBoot.SpringLearningPhase.exception.ResourceNotfoundException;
import com.SpringBoot.SpringLearningPhase.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;


    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntity = employeeRepository.findAll();
        return employeeEntity
                .stream()
                .map(employeeEntity1 -> modelMapper.map(employeeEntity1,EmployeeDTO.class))
                .collect(Collectors.toList());

    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity toSavetoDb = modelMapper.map(inputEmployee,EmployeeEntity.class);
        EmployeeEntity employeeEntity= employeeRepository.save(toSavetoDb);
        return modelMapper.map(employeeEntity,EmployeeDTO.class);

    }

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        isExistByEmployeeId(employeeId);
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO,EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity saveEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(saveEmployeeEntity,EmployeeDTO.class);
    }

    public void isExistByEmployeeId(Long employeeId)
    {
         boolean exist =employeeRepository.existsById(employeeId);
         if(!exist) throw new ResourceNotfoundException("Employee not found with id : "+employeeId);
    }

    public boolean deleteEmployeeById(Long employeeId) {
         isExistByEmployeeId(employeeId);
         employeeRepository.deleteById(employeeId);
         return true;

    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String,Object> updates )
    {
        isExistByEmployeeId(employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field,value)->{
            Field fieldtoBeUpdated = ReflectionUtils.findField(EmployeeEntity.class,field);
            fieldtoBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldtoBeUpdated,employeeEntity,value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);
    }
}
