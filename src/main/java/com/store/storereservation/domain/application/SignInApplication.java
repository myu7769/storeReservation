package com.store.storereservation.domain.application;

import com.zerobase.domain.common.UserType;
import com.zerobase.domain.config.JwtAuthenticationProvider;
import com.zerobase.domain.domain.SignInForm;
import com.zerobase.domain.domain.model.Customer;
import com.zerobase.domain.exception.CustomException;
import com.zerobase.domain.exception.ErrorCode;
import com.zerobase.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInApplication {

    private final CustomerService customerService;
    private final JwtAuthenticationProvider provider;
    public String customerLoginToken(SignInForm form) {
        // 1 로그인 가능여부
        Customer customer = customerService.finalValidCustomer(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));
        // 2 토큰 발행


        // 3. 토큰을 리스판스한다.

        return provider.createToken(customer.getEmail(), customer.getId(), UserType.CUSTOMER);
    }
}
