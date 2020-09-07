package com.agilityio.open_api_demo.repository.core;

import com.agilityio.open_api_demo.factory.model.CoreTemplateLoader;
import com.agilityio.open_api_demo.model.core.Department;
import com.github.javafaker.Faker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class DepartmentRepositoryImpl implements DepartmentRepository {
    @Override
    public Page<Department> findAllWithPageable(Pageable pageable) {
        return new PageImpl(CoreTemplateLoader.getDepartments(30), pageable, 30);
    }

    @Override
    public Department findById(String id) {
        if (!id.equals("0")) {
            Department department = CoreTemplateLoader.getDepartment();
            department.setId(id);
            return department;
        } else {
            return null;
        }
    }

    @Override
    public Department save(Department entity) {
        if (entity.getId() == null) {
            entity.setId(new Faker().internet().uuid());
        }
        return entity;
    }

    @Override
    public void delete(Department entity) {}
}
