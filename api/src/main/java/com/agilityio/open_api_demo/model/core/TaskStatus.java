package com.agilityio.open_api_demo.model.core;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum TaskStatus {
    IN_QUEUE, IN_PROGRESS, FINISHED
}
