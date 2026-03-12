package com.accenture.locationvoitures.controller;

import com.accenture.locationvoitures.controller.advice.ErrorDto;
import com.accenture.locationvoitures.service.dto.request.person.patch.CustomerPatchRequestDto;
import com.accenture.locationvoitures.service.dto.request.person.CustomerRequestDto;
import com.accenture.locationvoitures.service.dto.response.customer.person.CustomerResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Customers", description = "Customer managing API")
@RequestMapping("/customers")
public interface CustomerApi {

    @Operation(summary = "Create a new customer")
    @ApiResponse(responseCode = "201", description = "Customer created", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PostMapping
    ResponseEntity<Void> create(@RequestBody CustomerRequestDto dto);

    @Operation(summary = "Get Customer details with Basic Auth")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "401", description = "Not Authorized", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping("/account")
    ResponseEntity<CustomerResponseDto> readAccountDetails(@RequestHeader(name = "authorization") String base64Header);

    @Operation(summary = "Delete Customer with Basic Auth")
    @ApiResponse(responseCode = "204", description = "Customer successfully deleted")
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "401", description = "Not Authorized", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @DeleteMapping("/account")
    ResponseEntity<CustomerResponseDto> delete(@RequestHeader(name = "authorization") String base64Header);

    @Operation(summary = "Update partially Customer's details with Basic Auth")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "401", description = "Not Authorized", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "403", description = "Access forbidden", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PatchMapping("/account")
    ResponseEntity<CustomerResponseDto> patch(@RequestHeader(name = "authorization") String base64Header,@RequestBody CustomerPatchRequestDto dto);
}
