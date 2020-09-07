package com.agilityio.open_api_demo.controller.v1.api;

import com.agilityio.open_api_demo.dto.core.TaskDto;
import com.agilityio.open_api_demo.model.core.TaskStatus;
import com.agilityio.open_api_demo.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@Tag(name = "Task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "FindTasks", security = {@SecurityRequirement(name = "api-key")})
    @Parameters({
            @Parameter(in = ParameterIn.QUERY,
                    description = "Start date",
                    name = "start",
                    schema = @Schema(type = "string", format = "date-time")
            ),
            @Parameter(in = ParameterIn.QUERY,
                    description = "End date",
                    name = "end",
                    schema = @Schema(type = "string", format = "date-time")
            )
    })
    public List<TaskDto> findTasks(
            @RequestParam(name = "start", required = false) Date start,
            @RequestParam(name = "end", required = false) Date end,
            @RequestParam(name = "taskStatus", required = false) TaskStatus taskStatus
    ) {
        return taskService.findTasks(start, end, taskStatus);
    }
}
