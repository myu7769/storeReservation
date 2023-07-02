package com.store.storereservation.controller;

import com.store.storereservation.domain.customer.Customer;
import com.store.storereservation.domain.customer.CustomerSignUpForm;
import com.store.storereservation.exception.CustomException;
import com.store.storereservation.service.CustomerService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.store.storereservation.exception.ErrorCode.LOGIN_CHECK_FAIL;
import static com.store.storereservation.exception.ErrorCode.NOT_FOUND_USER;

@RestController
@RequiredArgsConstructor
public class SignController {

    private final CustomerService customerService;

    @PostMapping("/SignUp")
    public ResponseEntity<Customer> customerSignUp(@RequestBody CustomerSignUpForm form) {
        return ResponseEntity.ok(customerService.customerSignUp(form));
    }

    @PutMapping("/SignIn")
    public ResponseEntity<String> verifyCustomer(String email, String password) {
        Customer customer = customerService.customerSignIn(email, password)
                .orElseThrow(()-> new CustomException(LOGIN_CHECK_FAIL));

        return ResponseEntity.ok("Login Success!");
    }
}
