package com.accenture.locationvoitures.controller;

import com.accenture.locationvoitures.controller.advice.ErrorDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.MotorbikeRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.MotorbikePatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.MotorbikeAdminResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Motorbikes", description = "Motorbike managing API")
@RequestMapping("/vehicles/motorbikes")
public interface MotorbikeApi {
    @Operation(summary = "Create a new motorbike")
    @ApiResponse(responseCode = "201", description = "Motorbike created", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PostMapping
    ResponseEntity<Void> create(@RequestBody @Valid MotorbikeRequestDto dto, @RequestHeader(name = "authorization") String base64Header);

    @Operation(summary = "Get motorbikes, params : active={true,false}, outoffleet={true,false}")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping
    ResponseEntity<List<MotorbikeAdminResponseDto>> getMotorbikes(@RequestParam(required = false) Boolean active, @RequestParam(required = false) Boolean outoffleet, @RequestHeader(name = "authorization") String base64Header);

    @Operation(summary = "Get motorbike by id")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping("/{id}")
    ResponseEntity<MotorbikeAdminResponseDto> getById(@PathVariable("id") UUID id, @RequestHeader(name = "authorization") String base64Header);

    @Operation(summary = "Delete motorbike by id")
    @ApiResponse(responseCode = "204", description = "No content")
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable("id") UUID id, @RequestHeader(name = "authorization") String base64Header);

    @Operation(summary = "Patch bike")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PatchMapping("/{id}")
    ResponseEntity<MotorbikeAdminResponseDto> patch(@PathVariable("id") UUID id, @RequestBody @Valid MotorbikePatchRequestDto dto, @RequestHeader(name = "authorization") String base64Header);
}
