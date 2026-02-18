package com.bruno.spring.Project.Spring.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocConfig {

    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("Api Restful with Spring Boot 3.0")
                    .version("Version 1.0")
                    .description("Some description about your API")
                    .license(new License()
                            .name("Apache 2.0")));
        }
}
