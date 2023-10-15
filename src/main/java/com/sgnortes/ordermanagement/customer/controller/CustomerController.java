package com.sgnortes.ordermanagement.customer.controller;

import com.sgnortes.ordermanagement.common.constants.SwaggerConstants;
import com.sgnortes.ordermanagement.common.dto.PageDto;
import com.sgnortes.ordermanagement.customer.dto.CustomerDto;
import com.sgnortes.ordermanagement.customer.dto.CustomerPagingDto;
import com.sgnortes.ordermanagement.customer.service.interfaces.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.sgnortes.ordermanagement.common.constants.SecurityPermissions;

import java.util.List;

@Tag(name = "Customer API V1", description = "Endpoints to manage customers data.")
@RestController
@RequestMapping("customer/v1")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    private static final String CUSTOM_DTO_EXAMPLE_CREATE = "{\n" +
            "    \"name\": \"anyName\",\n" +
            "    \"surname\": \"anySurname\",\n" +
            "    \"country\": \"anyCountry\",\n" +
            "    \"address\": \"anyAddress\",\n" +
            "    \"postalCode\": \"anyPostalCode\",\n" +
            "    \"city\": \"anyCity\",\n" +
            "    \"email\": \"anyEmail@gmail.com\"\n" +
            "  }";

    @Operation(
            summary = "Get all customers paginated, filtered and sorted",
            description = SwaggerConstants.DESCRIPTION_PAGINATED_ENDPOINT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the customer/s", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PageDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request",content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer/s not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    @PreAuthorize(SecurityPermissions.READ_PERMISSION)
    public ResponseEntity<PageDto<CustomerDto>> getCustomerPagedAndSorted(@ParameterObject CustomerPagingDto dto){
        return ResponseEntity.ok(customerService.findAllPaginatedFilteredAndSorted(dto));
    }

    @Operation(summary = "Creates a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer created"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    @PreAuthorize(SecurityPermissions.ADMIN_PERMISSION)
    public ResponseEntity<Void> create(@RequestBody @Valid @Schema(example = CUSTOM_DTO_EXAMPLE_CREATE) CustomerDto dto){
            customerService.create(dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Updates a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping
    @PreAuthorize(SecurityPermissions.ADMIN_PERMISSION)
    public ResponseEntity<Void> update(@RequestBody @Valid CustomerDto dto){
        customerService.update(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Updates multiple customers using batching.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customers updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/batch")
    @PreAuthorize(SecurityPermissions.ADMIN_PERMISSION)
    public ResponseEntity<Void> batchUpdate(@RequestBody @Valid List<CustomerDto> dtos){
        customerService.batchUpdate(dtos);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
