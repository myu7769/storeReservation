package com.store.storereservation.domain.customer;
import com.store.storereservation.domain.config.Role;
import lombok.Getter;

@Getter
public class CustomerSignInForm {

    private String email;
    private String password;
}
