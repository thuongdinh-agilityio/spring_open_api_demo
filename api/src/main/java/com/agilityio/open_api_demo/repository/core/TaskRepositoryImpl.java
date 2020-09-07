package com.agilityio.open_api_demo.repository.core;

import com.agilityio.open_api_demo.factory.model.CoreTemplateLoader;
import com.agilityio.open_api_demo.model.core.Task;
import com.agilityio.open_api_demo.model.core.TaskStatus;
import com.github.javafaker.Faker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class TaskRepositoryImpl implements TaskRepository {
    @Override
    public Page<Task> findAllWithPageable(Pageable pageable) {
        return new PageImpl(CoreTemplateLoader.getTasks(30), pageable, 30);
    }

    @Override
    public Task findById(String id) {
        if (!id.equals("0")) {
            return CoreTemplateLoader.getTask()
                    .setId(id);
        } else {
            return null;
        }
    }

    @Override
    public Task save(Task entity) {
        if (entity.getId() == null) {
            entity.setId(new Faker().internet().uuid());
        }
        return entity;
    }

    @Override
    public void delete(Task entity) {}

    @Override
    public List<Task> findTasks(Date start, Date end, TaskStatus taskStatus) {
        return CoreTemplateLoader.getTasks(30);
    }
}
