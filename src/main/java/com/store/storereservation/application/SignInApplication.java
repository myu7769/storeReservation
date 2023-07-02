//package com.store.storereservation.application;
//
//import com.store.storereservation.config.JwtAuthenticationProvider;
//
//import com.store.storereservation.domain.customer.Customer;
//import com.store.storereservation.domain.customer.CustomerSignInForm;
//import com.store.storereservation.service.CustomerService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class SignInApplication {
//
//    private final CustomerService customerService;
//    private final JwtAuthenticationProvider provider;
//    public String customerLoginToken(CustomerSignInForm form) {
//        // 1 로그인 가능여부
//        Customer customer = customerService.finalValidCustomer(form.getEmail(), form.getPassword())
//                .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));
//        // 2 토큰 발행
//
//
//        // 3. 토큰을 리스판스한다.
//
//        return provider.createToken(customer.getEmail(), customer.getId(), UserType.CUSTOMER);
//    }
//}
