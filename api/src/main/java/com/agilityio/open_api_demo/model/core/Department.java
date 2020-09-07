package com.agilityio.open_api_demo.model.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Department {
    private String id;
    private String name;
    private Integer level;
    private Double revenue;
}
