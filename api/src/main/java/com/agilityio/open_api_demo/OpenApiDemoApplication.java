package com.agilityio.open_api_demo;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpenApiDemoApplication {

    public static void main(String[] args) {
        // Load Fixtures before start the application
        // TODO: make configuration for this and split out of the core business
        FixtureFactoryLoader.loadTemplates("com.agilityio.open_api_demo.factory");
        SpringApplication.run(OpenApiDemoApplication.class, args);
    }

}
