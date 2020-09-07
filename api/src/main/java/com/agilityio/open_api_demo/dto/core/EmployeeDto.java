package com.agilityio.open_api_demo.dto.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDto {
    @NotEmpty
    private String id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    @Email
    private String lastName;

    @NotEmpty
    private String mobileNumber;

    @NotEmpty
    private String email;

    @NotNull
    private DepartmentDto department;
}
