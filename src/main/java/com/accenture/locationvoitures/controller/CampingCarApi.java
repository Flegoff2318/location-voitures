package com.accenture.locationvoitures.controller;

import com.accenture.locationvoitures.controller.advice.ErrorDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.CampingCarRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.CampingCarPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.CampingCarAdminResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Tag(name = "Camping-cars", description = "Camping-car managing API")
@RequestMapping("/vehicles/camping-cars")
public interface CampingCarApi {
    @Operation(summary = "Create a new camping-car")
    @ApiResponse(responseCode = "201", description = "Camping-car created", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "401", description = "Not Authorized", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PostMapping
    ResponseEntity<Void> create(@RequestBody @Valid CampingCarRequestDto dto);

    @Operation(summary = "Get camping-cars, params : active={true,false}, outoffleet={true,false}")
    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CampingCarAdminResponseDto.class))))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "401", description = "Not Authorized", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping
    ResponseEntity<List<CampingCarAdminResponseDto>> getCampingCars(@RequestParam(required = false) Boolean active, @RequestParam(required = false) Boolean outoffleet);

    @Operation(summary = "Get camping-car By Id")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CampingCarAdminResponseDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "401", description = "Not Authorized", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "404", description = "Camping-car not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping("/{id}")
    ResponseEntity<CampingCarAdminResponseDto> getById(@PathVariable("id") UUID id);

    @Operation(summary = "Delete camping-car By Id")
    @ApiResponse(responseCode = "204", description = "No content")
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "401", description = "Not Authorized", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "404", description = "Camping-car not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable("id") UUID id);

    @Operation(summary = "Patch camping-car")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CampingCarAdminResponseDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "401", description = "Not Authorized", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "404", description = "Camping-car not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PatchMapping("/{id}")
    ResponseEntity<CampingCarAdminResponseDto> patch(@PathVariable("id") UUID id, @RequestBody @Valid CampingCarPatchRequestDto dto);
}
