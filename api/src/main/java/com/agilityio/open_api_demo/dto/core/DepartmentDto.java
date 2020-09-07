package com.agilityio.open_api_demo.dto.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentDto {
    @NotEmpty
    private String id;

    @NotEmpty
    private String name;

    @Min(0)
    @Max(10)
    private Integer level;

    @PositiveOrZero
    private Double revenue;
}
