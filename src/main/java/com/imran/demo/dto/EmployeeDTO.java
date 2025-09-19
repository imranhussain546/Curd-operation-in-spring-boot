package com.imran.demo.dto;




import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
// below annotation comes from lombok dependency it helps to autogenerate method
@Data
@AllArgsConstructor// generate arguments constructor
@NoArgsConstructor// without any arguments constructor
public class EmployeeDTO {

    private Long id;
    private String name;
    private LocalDate dateOfJoining;
    @JsonProperty("isActive")
    private boolean isActive;

}
