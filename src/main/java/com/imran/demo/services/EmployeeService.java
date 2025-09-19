package com.imran.demo.services;

import com.imran.demo.dto.EmployeeDTO;
import com.imran.demo.encryption.RSAKeyUtil;
import com.imran.demo.entities.EmployeeEntity;
import com.imran.demo.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    final EmployeeRepository employeeRepository;
    final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO getEmployeeById(Long id){
        EmployeeEntity employeeEntity=employeeRepository.getById(id);
       // return new EmployeeDTO(employeeEntity.getId(),employeeEntity.getName(),employeeEntity.getDateOfJoining(),employeeEntity.getIsActive());
        return modelMapper.map(employeeEntity,EmployeeDTO.class);
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO){
        EmployeeEntity employeeEntity=modelMapper.map(employeeDTO,EmployeeEntity.class);
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployee(){
        return employeeRepository.findAll()
                .stream().
                map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public boolean deleteEmployeeById(Long id){
        boolean isPresent =employeeRepository.existsById(id);
        if(!isPresent) return false;
         employeeRepository.deleteById(id);
         return true;
    }

    // update using put 
    public boolean updateEmployeeById(Long id, EmployeeDTO newEmployeeData) {
        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                    existingEmployee.setName(newEmployeeData.getName());
                    existingEmployee.setDateOfJoining(newEmployeeData.getDateOfJoining());
                    existingEmployee.setActive(newEmployeeData.isActive());
                    employeeRepository.save(existingEmployee);
                    return true;
                })
                .orElse(false);
    }
     // update using patch   
     public boolean updateEmployeeName(Long id, String name) {
        return employeeRepository.findById(id)
                .map(emp -> {
                    emp.setName(name);
                    employeeRepository.save(emp);
                    return true;
                })
                .orElse(false);
    }

    public Map<String, String> decryptAadhaar(Map<String, String> requestBody) {
        String name = requestBody.get("name");
        String encryptedAadhaar = requestBody.get("aadhaarNumber");

        Map<String, String> response = new HashMap<>();

        try {
            String decryptedAadhaar = RSAKeyUtil.decrypt(encryptedAadhaar);
            response.put("name", name);
            response.put("aadhaarNumber", decryptedAadhaar);
        } catch (Exception e) {
            response.put("error", "Decryption failed: " + e.getMessage());
        }

        return response;
    }
}
