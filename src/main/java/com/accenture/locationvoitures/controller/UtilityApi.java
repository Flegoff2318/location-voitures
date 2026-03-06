package com.accenture.locationvoitures.controller;

import com.accenture.locationvoitures.controller.advice.ErrorDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.UtilityRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.UtilityPatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.UtilityAdminResponseDto;
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

@Tag(name = "Utilities", description = "Utility managing API")
@RequestMapping("/vehicles/utilities")
public interface UtilityApi {
    @Operation(summary = "Create a new utility")
    @ApiResponse(responseCode = "201", description = "Utility created", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PostMapping
    ResponseEntity<Void> create(@RequestBody @Valid UtilityRequestDto dto, @RequestHeader(name = "authorization") String base64Header);

    @Operation(summary = "Get utilities, params : active={true,false}, outoffleet={true,false}")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping
    ResponseEntity<List<UtilityAdminResponseDto>> getUtilities(@RequestParam(required = false) Boolean active, @RequestParam(required = false) Boolean outoffleet, @RequestHeader(name = "authorization") String base64Header);

    @Operation(summary = "Get utility by id")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping("/{id}")
    ResponseEntity<UtilityAdminResponseDto> getById(@PathVariable("id") UUID id, @RequestHeader(name = "authorization") String base64Header);

    @Operation(summary = "Delete utility by id")
    @ApiResponse(responseCode = "204", description = "No content")
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable("id") UUID id, @RequestHeader(name = "authorization") String base64Header);

    @Operation(summary = "Patch utility")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PatchMapping("/{id}")
    ResponseEntity<UtilityAdminResponseDto> patch(@PathVariable("id") UUID id, @RequestBody @Valid UtilityPatchRequestDto dto, @RequestHeader(name = "authorization") String base64Header);
}
