package com.agilityio.open_api_demo.repository.core;

import com.agilityio.open_api_demo.model.core.Task;
import com.agilityio.open_api_demo.model.core.TaskStatus;
import com.agilityio.open_api_demo.repository.BaseCRUDRepository;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends BaseCRUDRepository<Task> {
    List<Task> findTasks(Date start, Date end, TaskStatus taskStatus);
}
