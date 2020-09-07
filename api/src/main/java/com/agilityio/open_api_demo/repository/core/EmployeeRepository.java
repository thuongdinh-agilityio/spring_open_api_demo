package com.agilityio.open_api_demo.repository.core;

import com.agilityio.open_api_demo.model.core.Employee;
import com.agilityio.open_api_demo.repository.BaseCRUDRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeRepository extends BaseCRUDRepository<Employee> {
    Page<Employee> findEmployeesByDepartmentId(String departmentId, Pageable pageable);
}
