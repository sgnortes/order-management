package com.sgnortes.ordermanagement.customer.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Customer entity
 * @author Sergio
 */
@Entity
@Table(name="CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    @Size(max= 50)
    @NotNull
    private String name;

    @Column(name = "SURNAME")
    @Size(max= 100)
    @NotNull
    private String surname;

    @Column(name = "COUNTRY")
    @Size(max= 50)
    @NotNull
    private String country;

    @Column(name = "ADDRESS")
    @Size(max= 200)
    @NotNull
    private String address;

    @Column(name = "POSTALCODE")
    @Size(max= 20)
    @NotNull
    private String postalCode;

    @Column(name = "CITY")
    @Size(max= 30)
    @NotNull
    private String city;

    @Column(name = "EMAIL")
    @Email
    private String email;

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

    public void setPostalCode(String postCode) {
        this.postalCode = postCode;
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
