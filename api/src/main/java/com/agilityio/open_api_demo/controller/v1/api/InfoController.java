package com.agilityio.open_api_demo.controller.v1.api;

import com.agilityio.open_api_demo.dto.response.PingResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Application Status")
public class InfoController {

    @GetMapping("/ping")
    @Operation(summary = "Ping")
    PingResponseDto ping() {
        return new PingResponseDto().setStatus("UP");
    }

}
