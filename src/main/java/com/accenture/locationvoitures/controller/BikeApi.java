package com.accenture.locationvoitures.controller;

import com.accenture.locationvoitures.controller.advice.ErrorDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.BikeRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.BikePatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.BikeAdminResponseDto;
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

@Tag(name = "Bikes", description = "Bike managing API")
@RequestMapping("/vehicles/bikes")
public interface BikeApi {
    @Operation(summary = "Create a new bike")
    @ApiResponse(responseCode = "201", description = "Bike created", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PostMapping
    ResponseEntity<Void> create(@RequestBody @Valid BikeRequestDto dto, @RequestHeader(name = "authorization") String base64Header);

    @Operation(summary = "Get bikes, params : active={true,false}, outoffleet={true,false}")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping
    ResponseEntity<List<BikeAdminResponseDto>> getBikes(@RequestParam(required = false) Boolean active, @RequestParam(required = false) Boolean outoffleet, @RequestHeader(name = "authorization") String base64Header);

    @Operation(summary = "Get bike by id")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping("/{id}")
    ResponseEntity<BikeAdminResponseDto> getById(@PathVariable("id") UUID id, @RequestHeader(name = "authorization") String base64Header);

    @Operation(summary = "Delete bike by id")
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
    ResponseEntity<BikeAdminResponseDto> patch(@PathVariable("id") UUID id, @RequestBody @Valid BikePatchRequestDto dto, @RequestHeader(name = "authorization") String base64Header);
}
