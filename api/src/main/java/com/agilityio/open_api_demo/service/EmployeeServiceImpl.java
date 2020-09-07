package com.agilityio.open_api_demo.service;

import com.agilityio.open_api_demo.dto.core.EmployeeDto;
import com.agilityio.open_api_demo.dto.mapper.EmployeeMapper;
import com.agilityio.open_api_demo.dto.request.EmployeeRequestDto;
import com.agilityio.open_api_demo.model.core.Department;
import com.agilityio.open_api_demo.model.core.Employee;
import com.agilityio.open_api_demo.repository.core.DepartmentRepository;
import com.agilityio.open_api_demo.repository.core.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import static com.agilityio.open_api_demo.exception.EntityType.*;

@Component
public class EmployeeServiceImpl extends BaseService implements EmployeeService  {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    private Employee updateEmployee(EmployeeRequestDto employeeDto) {
        Department department = departmentRepository.findById(employeeDto.getDepartmentId());
        if (department == null) {
            throw exception(DEPARTMENT, employeeDto.getDepartmentId());
        }

        Employee employee = new Employee();
        employee.setEmail(employeeDto.getEmail());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setMobileNumber(employeeDto.getMobileNumber());
        employee.setDepartment(department);

        return employee;
    }

    @Override
    public EmployeeDto getEmployeeById(String id) {
        Employee employee = employeeRepository.findById(id);
        if (employee != null) {
            return EmployeeMapper.toEmployeeDto(employee);
        }
        throw exception(EMPLOYEE, id);
    }

    @Override
    public Page<EmployeeDto> getAllEmployees(Pageable pageable, Sort sort) {
        return EmployeeMapper.toPageEmployeeDtoList(employeeRepository.findAllWithPageable(pageable));
    }

    @Override
    public Page<EmployeeDto> getEmployeesByDepartmentId(String departmentId, Pageable pageable) {
        return EmployeeMapper.toPageEmployeeDtoList(employeeRepository.findEmployeesByDepartmentId(departmentId, pageable));
    }

    @Override
    public EmployeeDto createEmployee(EmployeeRequestDto employeeDto) {
        Employee employee = updateEmployee(employeeDto);
        return EmployeeMapper.toEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto updateEmployee(String employeeId, EmployeeRequestDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeId);
        if (employee != null) {
            employee = updateEmployee(employeeDto);
            employee.setId(employeeId);
            return EmployeeMapper.toEmployeeDto(employeeRepository.save(employee));
        }
        throw exception(EMPLOYEE, employeeId);
    }

    @Override
    public void deleteEmployeeById(String id) {
        Employee employee = employeeRepository.findById(id);
        if (employee == null) {
            throw exception(EMPLOYEE, id);
        }
    }

}
