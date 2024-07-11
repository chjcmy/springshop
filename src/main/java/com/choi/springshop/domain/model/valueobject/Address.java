package com.choi.springshop.domain.model.valueobject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private final String street;
    private final String city;
    private final String state;
    private final String country;
    private final String zipCode;

    public Address(String street, String city, String state, String country, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }
}