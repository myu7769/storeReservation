package com.store.storereservation.domain.controller;

import com.store.storereservation.domain.customer.CustomerSignInForm;
import com.zerobase.domain.application.SignInApplication;
import com.zerobase.domain.domain.SignInForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signIn")
@RequiredArgsConstructor
public class SignInController {

    private final SignInApplication signInApplication;

    @PostMapping("/customer")
    public ResponseEntity<String> signInCustomer(@RequestBody CustomerSignInForm form) {

        return ResponseEntity.ok(signInApplication.customerLoginToken(form));
    }
}
