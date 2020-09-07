package com.agilityio.open_api_demo.dto.mapper;

import com.agilityio.open_api_demo.common.util.LooseModelMapper;
import com.agilityio.open_api_demo.dto.core.TaskDto;
import com.agilityio.open_api_demo.model.core.Task;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskMapper {
    public static TaskDto toTaskDto(Task task) {
        return LooseModelMapper.getInstance().map(task, TaskDto.class);
    }

    public static List<TaskDto> toTaskDtoList(List<Task> tasks) {
        return LooseModelMapper.getInstance().map(tasks, new TypeToken<List<TaskDto>>() {}.getType());
    }
}
