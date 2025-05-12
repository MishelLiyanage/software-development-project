package com.SDP.project.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManageEmployeeDto {
    private String username;

    private String firstName;

    private String lastName;

    private String address;

    private String contactNo;

    private String departmentName;
}
