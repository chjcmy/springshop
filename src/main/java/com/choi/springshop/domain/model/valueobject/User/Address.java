package com.choi.springshop.domain.model.valueobject.User;

import com.choi.springshop.domain.model.exception.InvalidAddressException;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

    private String street;

    private String city;

    private String state;

    private String country;

    private String zipCode;

    public Address(String street, String city, String state, String country, String zipCode) throws InvalidAddressException {
        if (street.isBlank() || city.isBlank() || state.isBlank() || country.isBlank() || zipCode.isBlank()) {
            throw new InvalidAddressException("All address fields are required.");
        }
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }
}