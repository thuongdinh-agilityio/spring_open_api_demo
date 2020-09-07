package com.agilityio.open_api_demo.service;

import com.agilityio.open_api_demo.exception.DemoException;
import com.agilityio.open_api_demo.exception.EntityType;
import com.agilityio.open_api_demo.exception.ExceptionType;

public class BaseService {
    protected RuntimeException exception(EntityType entityType, String... args) {
        return DemoException.throwException(entityType, ExceptionType.ENTITY_NOT_FOUND, args);
    }
}
