package com.sgnortes.ordermanagement.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Dto for customer
 * @author Sergio
 */
public class CustomerDto {

    @Min(1)
    private Long id;

    @Size(max= 50)
    @NotNull
    private String name;

    @Size(max= 100)
    @NotNull
    private String surname;

    @Size(max= 50)
    @NotNull
    private String country;

    @Size(max= 200)
    @NotNull
    private String address;

    @Size(max= 20)
    @NotNull
    private String postalCode;

    @Size(max= 30)
    @NotNull
    private String city;

    @Email
    private String email;

    // TODO: investigate why Lombok Getter and Setter annotation is not working
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}