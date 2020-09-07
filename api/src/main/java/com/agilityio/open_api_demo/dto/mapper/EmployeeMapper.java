package com.agilityio.open_api_demo.dto.mapper;

import com.agilityio.open_api_demo.common.util.LooseModelMapper;
import com.agilityio.open_api_demo.dto.core.EmployeeDto;
import com.agilityio.open_api_demo.model.core.Employee;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;

import java.util.List;

public class EmployeeMapper {

    public static EmployeeDto toEmployeeDto(Employee employee) {
        return LooseModelMapper.getInstance().map(employee, EmployeeDto.class);
    }

    public static List<EmployeeDto> toEmployeeDtoList(List<Employee> employees) {
        return LooseModelMapper.getInstance().map(employees, new TypeToken<List<EmployeeDto>>() {}.getType());
    }

    public static Page<EmployeeDto> toPageEmployeeDtoList(Page<Employee> employees) {
        return LooseModelMapper.getInstance().map(employees, new TypeToken<Page<EmployeeDto>>() {}.getType());
    }

    public static Employee toEmployee(EmployeeDto employeeDto) {
        return LooseModelMapper.getInstance().map(employeeDto, Employee.class);
    }

}
