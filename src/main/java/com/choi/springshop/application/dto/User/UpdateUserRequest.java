package com.choi.springshop.application.dto.User;

import com.choi.springshop.domain.model.valueobject.User.Address;
import com.choi.springshop.domain.model.valueobject.User.PhoneNumber;
import com.choi.springshop.domain.model.valueobject.User.UserEmail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    private PhoneNumber phoneNumber;
    private UserEmail email;
    private Address address;
}
