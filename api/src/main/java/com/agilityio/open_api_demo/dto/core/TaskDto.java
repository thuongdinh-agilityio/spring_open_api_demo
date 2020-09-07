package com.agilityio.open_api_demo.dto.core;

import com.agilityio.open_api_demo.model.core.TaskStatus;
import com.agilityio.open_api_demo.common.validation.ValueOfEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDto {
    @NotEmpty
    private String id;

    @NotEmpty
    private String title;

    private String content;

    @Min(0)
    @Max(10)
    private Integer level;

    @ValueOfEnum(enumClass = TaskStatus.class)
    private TaskStatus taskStatus;

    @NotNull
    @Schema(type = "string", format = "date-time")
    private Date start;

    @NotNull
    @Schema(type = "string", format = "date-time")
    private Date end;
}
