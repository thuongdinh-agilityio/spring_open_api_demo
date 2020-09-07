package com.agilityio.open_api_demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI configOpenAPI() {
        return new OpenAPI()

                // General information
                .info(new Info().title("Open API Demo - REST APIs")
                        .description("Demonstration about Open API and Spring Restful.")
                        .version("v1.0.0")
                        .license(
                                new License()
                                        .name("Apache 2.0")
                                        .url("https://github.com/thuongdinh-agilityio")
                        ))

                // Define global api-key which be used for auth(z)
                .components(new Components()
                        //API Key, see: https://swagger.io/docs/specification/authentication/api-keys/
                        .addSecuritySchemes("api-key", new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .name("X-API-KEY")
                        )
                ).addSecurityItem(new SecurityRequirement()
                        .addList("api-key")
                );
    }

}
