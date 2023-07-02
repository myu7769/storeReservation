package com.store.storereservation.domain.customer;
import com.store.storereservation.common.UserType;
import com.store.storereservation.domain.config.Role;
import lombok.Getter;

@Getter
public class CustomerSignUpForm {

    private String email;
    private String name;
    private String password;
    private UserType role;
}
