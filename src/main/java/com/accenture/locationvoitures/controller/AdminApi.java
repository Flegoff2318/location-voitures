package com.accenture.locationvoitures.controller;

import com.accenture.locationvoitures.controller.advice.ErrorDto;
import com.accenture.locationvoitures.service.dto.request.AdminRequestDto;
import com.accenture.locationvoitures.service.dto.response.AdminResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Admins", description = "Admin managing API")
@RequestMapping("/admins")
public interface AdminApi {
    @Operation(summary = "Create a new admin")
    @ApiResponse(responseCode = "201", description = "Admin created", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PostMapping
    ResponseEntity<Void> create(@RequestBody AdminRequestDto dto);

    @Operation(summary = "Get Admin details By Basic Auth")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "404", description = "Admin not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping("/account")
    ResponseEntity<AdminResponseDto> readAccountDetails(@RequestHeader(name = "authorization") String base64Header);

    @Operation(summary = "Delete Admin By Basic Auth")
    @ApiResponse(responseCode = "204", description = "Admin successfully deleted")
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "404", description = "Admin not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @DeleteMapping
    ResponseEntity<AdminResponseDto> delete(@RequestHeader(name = "authorization") String base64Header);
}
