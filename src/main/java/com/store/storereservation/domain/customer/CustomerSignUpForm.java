package com.store.storereservation.domain.customer;
import com.store.storereservation.domain.config.Role;
import lombok.Getter;

@Getter
public class CustomerSignUpForm {

    private String email;
    private String name;
    private String password;
    private Role role;
}
