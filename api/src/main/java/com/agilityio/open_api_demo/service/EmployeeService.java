package com.agilityio.open_api_demo.service;

import com.agilityio.open_api_demo.dto.core.EmployeeDto;
import com.agilityio.open_api_demo.dto.request.EmployeeRequestDto;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface EmployeeService {
    EmployeeDto getEmployeeById(String id);
    Page<EmployeeDto> getAllEmployees(Pageable pageable, Sort sort);
    Page<EmployeeDto> getEmployeesByDepartmentId(String departmentId, Pageable pageable);
    EmployeeDto createEmployee(EmployeeRequestDto employeeDto);
    EmployeeDto updateEmployee(String employeeId, EmployeeRequestDto employeeDto);
    void deleteEmployeeById(String id);
}
