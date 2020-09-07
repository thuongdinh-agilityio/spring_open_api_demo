# OPEN API DEMO

This repository contains source code for a demonstration about RESTful APIs (Java Spring) & Open API Generator for Angular.
 - How to implement Restful APIs with Spring framework & apply OpenAPI definition
 - How to export OpenAPI Doc in Json/YAML format
 - How to generate API client code for an Angular project
 - How to write e2e testing code for generated API client

## Requirements

 - NodeJS
 - Angular
 - nx.dev
 - Docker
 - Java (Openjdk 11.0.8)
 - openapi-generator-cli (v4.3.1)

## How to run the example

### 1. Activate the terminal commands

```$ source activate.sh```

### 2. Build & start the APIs server

```$ do-build-run-api```

After this command finished success, visit those links to access APIs documents:
 - [Swagger](http://localhost:8080/swagger-ui/index.html?configUrl=/api-docs/swagger-config)
 - [OpenAPI Spec (Json)](http://localhost:8080/api-docs)

### 3. Run web APIs test cases

```$ do-web-run-api-test```

### 4. Re-generate the web APIs if have changes in APIs

```$ do-api-gen```

Check generated APIs client in folder `web/libs/api/src/lib` and specs of testing APIs in `web/libs/api/src/lib/specs`.

## Implement OpenAPI Doc in Spring Guidelines

### Add required libraries 

Add springdoc libs to [build.gradle](./api/build.gradle) file.
```
// OpenAPI
implementation "org.springdoc:springdoc-openapi-ui:1.4.4"
// Support Pageable, sorted etc... without it some params will be fail
// when render on the swagger-ui
implementation "org.springdoc:springdoc-openapi-data-rest:1.4.6"
```

### Add SwaggerConfiguration

Please check file [SwaggerConfiguration.java](./api/src/main/java/com/agilityio/open_api_demo/config/SwaggerConfiguration.java) for more detail.

### Implement Dto classes

**Notes:**
 - Dto is a POJO class
 - Dto uses lombok to simplify get/set
 - Dto uses javax.validation to simplify data validation for APIs (see @Valid in controller classes)

Example [EmployeeDto.java](./api/src/main/java/com/agilityio/open_api_demo/dto/core/EmployeeDto.java).

```java
package com.agilityio.open_api_demo.dto.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDto {
    @NotEmpty
    private String id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    @Email
    private String lastName;

    @NotEmpty
    private String mobileNumber;

    @NotEmpty
    private String email;

    @NotNull
    private DepartmentDto department;
}
```

### Add OpenAPI Annotation for Enums

The annotation `@Schema(enumAsRef = true)` helps springdoc identify & generate enum for APIs client code.
Check generated client code of web [taskStatus.ts](./web/libs/api/src/lib/model/taskStatus.ts) to see the result. Without this annotation, the variable will have string type.

```java
package com.agilityio.open_api_demo.model.core;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum TaskStatus {
    IN_QUEUE, IN_PROGRESS, FINISHED
}
``` 

### Add OpenAPI Annotation for controller classes

Define `@Tag` which helps to display nicely name of APIs group in the APIs doc & Swagger [TaskController.java](./api/src/main/java/com/agilityio/open_api_demo/controller/v1/api/TaskController.java). 
```java
@RestController
@RequestMapping("/api/v1/tasks")
@Tag(name = "Task")
public class TaskController {
    ...
}
```

`@Operation` annotations to override API's function id & support `api-key` [EmployeeController.java](./api/src/main/java/com/agilityio/open_api_demo/controller/v1/api/EmployeeController.java).
*Note:* 
 - If not add operationId, OpenAPI will get the name of function, so be careful with builtIn names of language like `delete`, need to be customized to generate the right API function name.
 - `@Valid` is for auto validation input data based on the javax.validation defined in the POJO classes. 
```java
@GetMapping
@Operation(security = {@SecurityRequirement(name = "api-key")}, operationId = "getAll")
Page<EmployeeDto> getAll(Pageable pageable, Sort sort) {
    return employeeService.getAllEmployees(pageable, sort);
}

@GetMapping(value = "/{id}")
@Operation(security = {@SecurityRequirement(name = "api-key")})
EmployeeDto getById(@PathVariable("id") String id) {
    return employeeService.getEmployeeById(id);
}

@PostMapping
@ResponseStatus(HttpStatus.CREATED)
@Operation(security = {@SecurityRequirement(name = "api-key")})
public EmployeeDto createEmployee(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
    return employeeService.createEmployee(employeeRequestDto);
}
```

API with `@Parameter` annotations helps to override API's parameters [TaskController.java](./api/src/main/java/com/agilityio/open_api_demo/controller/v1/api/TaskController.java). 
```java
@GetMapping
@ResponseStatus(HttpStatus.OK)
@Operation(summary = "FindTasks", security = {@SecurityRequirement(name = "api-key")})
@Parameters({
        @Parameter(in = ParameterIn.QUERY,
                description = "Start date",
                name = "start",
                schema = @Schema(type = "string", format = "date-time")
        ),
        @Parameter(in = ParameterIn.QUERY,
                description = "End date",
                name = "end",
                schema = @Schema(type = "string", format = "date-time")
        )
})
public List<TaskDto> findTasks(
        @RequestParam(name = "start", required = false) Date start,
        @RequestParam(name = "end", required = false) Date end,
        @RequestParam(name = "taskStatus", required = false) TaskStatus taskStatus
) {
    return taskService.findTasks(start, end, taskStatus);
}
```

### Custom Exception handling and classes

This will help to show the Error response classes in OpenAPI doc, Swagger & generated client code.
 - Java classes in the package [exception](./api/src/main/java/com/agilityio/open_api_demo/exception).
 - Generated APIs client code in the [errorResponse.ts](./web/libs/api/src/lib/model/errorResponse.ts) file.

## Notes

1. The openapi-generator version 4.3.1 does not generate Date type for format=date-time, only support string for date/date-time type. Still don't have solution for now.
 
## References
 - https://dotnetthoughts.net/how-to-generate-angular-code-from-openapi-specifications
 - https://openapi-generator.tech/docs/generators/typescript-angular
 - https://blog.armstrongconsulting.com/movie-draft/
 - https://spring.io/guides/tutorials/rest/
 - https://www.baeldung.com/spring-rest-openapi-documentation
 - https://medium.com/the-resonant-web/spring-boot-2-0-starter-kit-part-1-23ddff0c7da2
 - https://github.com/khandelwal-arpit/springboot-starterkit
 - https://github.com/int128/gradle-swagger-generator-plugin
 - https://www.baeldung.com/java-modelmapper-lists
 - https://reflectoring.io/spring-boot-paging/
 - https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
 - https://dzone.com/articles/doing-more-with-swaggger-and-spring
 - https://www.baeldung.com/spring-boot-bean-validation