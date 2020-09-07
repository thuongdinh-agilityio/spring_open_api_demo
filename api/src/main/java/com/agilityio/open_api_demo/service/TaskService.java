package com.agilityio.open_api_demo.service;

import com.agilityio.open_api_demo.dto.core.TaskDto;
import com.agilityio.open_api_demo.model.core.TaskStatus;

import java.util.Date;
import java.util.List;

public interface TaskService {
    List<TaskDto> findTasks(Date start, Date end, TaskStatus taskStatus);
}
