package com.agilityio.open_api_demo.service;

import com.agilityio.open_api_demo.dto.core.TaskDto;
import com.agilityio.open_api_demo.dto.mapper.TaskMapper;
import com.agilityio.open_api_demo.model.core.TaskStatus;
import com.agilityio.open_api_demo.repository.core.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskDto> findTasks(Date start, Date end, TaskStatus taskStatus) {
        return TaskMapper.toTaskDtoList(taskRepository.findTasks(start, end, taskStatus));
    }
}
