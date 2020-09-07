package com.agilityio.open_api_demo.controller.v1.api;

import com.agilityio.open_api_demo.dto.core.EmployeeDto;
import com.agilityio.open_api_demo.dto.request.EmployeeRequestDto;
import com.agilityio.open_api_demo.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/employees")
@Tag(name = "Employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @Operation(security = {@SecurityRequirement(name = "api-key")}, operationId = "getAll")
    Page<EmployeeDto> getAll(Pageable pageable, Sort sort) {
        return employeeService.getAllEmployees(pageable, sort);
    }

    @GetMapping(value = "/{id}")
    @Operation(security = {@SecurityRequirement(name = "api-key")})
    EmployeeDto getById(@PathVariable("id") String id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(security = {@SecurityRequirement(name = "api-key")})
    public EmployeeDto createEmployee(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        return employeeService.createEmployee(employeeRequestDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(security = {@SecurityRequirement(name = "api-key")})
    public EmployeeDto updateEmployee(
            @PathVariable("id") String id,
            @Valid @RequestBody EmployeeRequestDto employeeRequestDto
    ) {
        return employeeService.updateEmployee(id, employeeRequestDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(security = {@SecurityRequirement(name = "api-key")})
    public void deleteEmployee(@PathVariable("id") String id) {
        employeeService.deleteEmployeeById(id);
    }
}
