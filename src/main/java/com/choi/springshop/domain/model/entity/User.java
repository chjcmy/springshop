package com.choi.springshop.domain.model.entity;

import com.choi.springshop.domain.model.valueobject.Cart.CartStatus;
import com.choi.springshop.domain.model.valueobject.User.Address;
import com.choi.springshop.domain.model.valueobject.User.PhoneNumber;
import com.choi.springshop.domain.model.valueobject.User.UserEmail;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.HashSet;
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
    @JsonManagedReference
    private Set<Order> orders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Cart> carts = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "active_cart_id")
    private Cart activeCart;

    public void addCart(Cart cart) {
        carts.add(cart);
        cart.setUser(this);
        if (cart.getStatus() == CartStatus.ACTIVE) {
            setActiveCart(cart);
        }
    }

    public void setActiveCart(Cart cart) {
        if (activeCart != null && activeCart != cart) {
            activeCart.setStatus(CartStatus.ABANDONED);
        }
        activeCart = cart;
        cart.setStatus(CartStatus.ACTIVE);
    }
}
