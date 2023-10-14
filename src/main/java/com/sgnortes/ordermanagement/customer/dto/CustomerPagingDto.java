package com.sgnortes.ordermanagement.customer.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Dto created specifically for searching paged data of Customers.
 * @author Sergio
 */
public class CustomerPagingDto {

    @Parameter(description="id", example ="eq:1")
    private String id;

    @Parameter(description="name of customer", example ="eq:Sergio")
    private String name;

    @Parameter(description="surname of customer", example ="eq:Garcia")
    private String surname;
    @Parameter(description="country of customer", example ="eq:Spain")
    private String country;

    @Parameter(description="address of customer", example ="eq:Avenida Vicente Savall, 1")
    private String address;

    @Parameter(description="postal code of the city", example ="eq:03690")
    private String postalCode;

    @Parameter(description="city customer lives in", example ="eq:San Vicente del Raspeig")
    private String city;

    @Parameter(description="email of customer", example ="eq:sergio@gmail.com")
    private String email;

    @Parameter(description="Page number we are retrieving.", example ="0")
    private Integer page;

    @Parameter(description="Size of the page we are retrieving.", example = "10")
    private Integer size;

    @Parameter(description = "Order to apply. It should follow the following format: 'fieldName,order', where order can be 'asc' for ascending and 'desc' for descending. You can include multiple order criteria.")
    @ArraySchema(schema = @Schema( type = "string", example = "id:asc"))
    private String[] sort;

    // TODO: investigate why Lombok doesn't generate Getters and Setters correctly.

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String[] getSort() {
        return sort;
    }

    public void setSort(String[] sort) {
        this.sort = sort;
    }
}
