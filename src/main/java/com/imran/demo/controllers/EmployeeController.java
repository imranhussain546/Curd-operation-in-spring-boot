package com.imran.demo.controllers;

import com.imran.demo.services.EmployeeService;
import jakarta.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.imran.demo.dto.EmployeeDTO;
import com.imran.demo.dto.ApiResponse;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/employees")
    public EmployeeDTO getEmployee() {
        return new EmployeeDTO(1l,"Imran Hussain",LocalDate.of(2024,9,12),false);
    }

    // Path variable
    @GetMapping(path = "/employees/{id}")
    public EmployeeDTO getEmployee(@PathVariable("id") Long employeeId){
        return new EmployeeDTO(employeeId,"Imran Hussain", LocalDate.of(2024,9,12), true);
    }
//    // path params
//    @GetMapping(path = "/employees/byId")
//    public  EmployeeDTO getEmployees(@PathParam("employeeId") Long employeeId){
//        return new EmployeeDTO(employeeId,"Imran Hussain", LocalDate.of(2024,9,12), true);
//    }

    // path params
    @GetMapping(path = "/employees/byId")
    public  EmployeeDTO getEmployees(@PathParam("employeeId") Long employeeId){
        return employeeService.getEmployeeById(employeeId);
    }

    //post api
    // request body
//    @PostMapping("/employees/create")
//    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeDTO){
//        return  employeeService.createNewEmployee(employeeDTO);
//    }
    @PostMapping("/employees/create")
    public ResponseEntity<ApiResponse<EmployeeDTO>> createNewEmployee(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO userCeated =  employeeService.createNewEmployee(employeeDTO);
        ApiResponse<EmployeeDTO> response = new ApiResponse<>("Employee Created successful", userCeated);
        return  ResponseEntity.ok(response);
    }

    @PostMapping(value = "/employees/encryption")
    public ResponseEntity<ApiResponse<Map<String, String>>> demoEncryption(@RequestBody Map<String, String> requestBody) {
        Map<String, String> decryptedData = employeeService.decryptAadhaar(requestBody);
        ApiResponse<Map<String, String>> response = new ApiResponse<>("Decryption successful", decryptedData);
        return ResponseEntity.ok(response);
    }




    // all employee
    @GetMapping(value = "/employee/all")
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployee();
        ApiResponse<List<EmployeeDTO>> response = new ApiResponse<>(
                "Employees fetched successfully", employees);
        return ResponseEntity.ok(response);
    }



    // delete employee
//    @DeleteMapping("/employee/delete")
//    public ResponseEntity<ApiResponse<Map<String, String>>> deleteEmployeeById(@PathParam("employeeId") Long employeeId) {
//        boolean deletedEmployee = employeeService.deleteEmployeeById(employeeId);
//        Map<String, String> response = new HashMap<>();
//        ApiResponse<Map<String, String>> response = new ApiResponse<>(
//                "Employees fetched successfully", deletedEmployee);
//        return ResponseEntity.ok(response);
//    }

// for update all field
@PutMapping("/updateEmployeeById/allField/{id}") // putmapping update all field 
public ResponseEntity<String> updateAllFiled(@PathVariable Long id,@RequestBody EmployeeDTO employeeDTO) {
    boolean updated = employeeService.updateEmployeeById(id, employeeDTO);
    
    if (updated) {
            return ResponseEntity.ok("Employee updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Employee not found with id " + id);
        }
}
//for update only one field
@PatchMapping("updateEmployeeNameById/{id}") // PatchMapping update only one field
    public ResponseEntity<String> updateEmployeeName(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {

        String newName = request.get("name");

        boolean updated = employeeService.updateEmployeeName(id, newName);

        if (updated) {
            return ResponseEntity.ok("Employee name updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Employee not found with id " + id);
        }
    }


}
