package com.promineotech.jeep.controller;

import com.promineotech.jeep.entity.Jeep;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
@RequestMapping("/jeeps")
@OpenAPIDefinition(info = @Info(title = "Jeep Sales Service"),
        servers = {@Server(url= "http://localhost:8800", description = "Local server.")})

public interface JeepSalesController {
    @Operation(
            summary= "Returns a list of jeeps",
            description = "Returns a list of jeeps given a particular jeep and trim model",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A list of jeeps is returned.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Jeep.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No jeeps found according to input.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = "application/json")),
            },
            parameters = {
                    @Parameter(name = "model", allowEmptyValue = false, required = false,
                            description = "The model name(i.e, 'WRANGLER')"),
                    @Parameter(name = "trim", allowEmptyValue = false, required = false,
                            description = "The trim level(i.e, 'Sport')")

            }
    )
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<Jeep> fetchJeeps(@RequestParam(required = false)
                          String model,
                          @RequestParam(required = false)
                          String trim);

    }

