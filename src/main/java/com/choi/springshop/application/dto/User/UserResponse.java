package com.choi.springshop.application.dto.User;

import com.choi.springshop.domain.model.valueobject.User.Address;
import com.choi.springshop.domain.model.valueobject.User.PhoneNumber;
import com.choi.springshop.domain.model.valueobject.User.UserEmail;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserResponse {
    private String name;
    private PhoneNumber phoneNumber;
    private UserEmail userEmail;
    private Address address;


    public UserResponse(String name, PhoneNumber phoneNumber,UserEmail userEmail, Address address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.userEmail = userEmail;
        this.address = address;
    }
}
