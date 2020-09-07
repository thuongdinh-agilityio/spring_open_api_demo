package com.agilityio.open_api_demo.repository.core;

import com.agilityio.open_api_demo.factory.model.CoreTemplateLoader;
import com.agilityio.open_api_demo.model.core.Department;
import com.agilityio.open_api_demo.model.core.Employee;
import com.github.javafaker.Faker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Override
    public Page<Employee> findEmployeesByDepartmentId(String departmentId, Pageable pageable) {
        List<Employee> employeeList = CoreTemplateLoader.getEmployees(20);
        for (Employee employee: employeeList) {
            Department employeeDepartment = employee.getDepartment();
            employeeDepartment.setId(departmentId);
            employee.setDepartment(employeeDepartment);
        }

        return new PageImpl(employeeList, pageable, employeeList.size());
    }

    @Override
    public Page<Employee> findAllWithPageable(Pageable pageable) {
        return new PageImpl(CoreTemplateLoader.getEmployees(30), pageable, 30);
    }

    @Override
    public Employee findById(String id) {
        if (!id.equals("0")) {
            Employee employee = CoreTemplateLoader.getEmployee();
            employee.setId(id);
            return employee;
        } else {
            return null;
        }
    }

    @Override
    public Employee save(Employee entity) {
        if (entity.getId() == null) {
            entity.setId(new Faker().internet().uuid());
        }
        return entity;
    }

    @Override
    public void delete(Employee entity) {
    }
}
