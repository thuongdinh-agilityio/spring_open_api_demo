package com.agilityio.open_api_demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("com.agilityio.open-api-demo.global")
public class GlobalProperties {
    private String pathsToMatch;
    private String allowedOrigins;
}
