package com.choi.springshop.domain.model.entity;

import com.choi.springshop.domain.model.valueobject.User.Address;
import com.choi.springshop.domain.model.valueobject.User.PhoneNumber;
import com.choi.springshop.domain.model.valueobject.User.UserEmail;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Embedded
    private UserEmail email;

    @Embedded
    private Address address;

    @Embedded
    private PhoneNumber phoneNumber;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders;
}
