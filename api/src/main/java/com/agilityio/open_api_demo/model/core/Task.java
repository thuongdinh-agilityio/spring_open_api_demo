package com.agilityio.open_api_demo.model.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Task {
    private String id;
    private String title;
    private String content;
    private TaskStatus taskStatus;
    private Employee employee;
    private Date start;
    private Date end;
}
