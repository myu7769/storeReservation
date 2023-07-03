package com.store.storereservation.service;


import com.store.storereservation.common.UserType;
import com.store.storereservation.config.JwtAuthenticationProvider;
import com.store.storereservation.domain.customer.Customer;
import com.store.storereservation.domain.customer.CustomerSignUpForm;
import com.store.storereservation.exception.CustomException;
import com.store.storereservation.exception.ErrorCode;
import com.store.storereservation.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final JwtAuthenticationProvider provider;

    public Customer customerSignUp(CustomerSignUpForm form) {

        if (customerRepository.findByEmail(form.getEmail().toLowerCase(Locale.ROOT)).isPresent()) {
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        }else{
            return customerRepository.save(Customer.from(form));
        }

    }

    public Optional<Customer> customerSignIn(String email, String password) {
            return customerRepository.findByEmail(email)
                    .stream().filter(customer ->
//                            customer.isVerify() &&
                                    customer.getPassword().equals(password))
                    .findFirst();
    }


    /**
     *
     * 아래 메소드들은 추루 토큰 구현을 위해 작성해둠
     *
     * @param form
     * @return
     */

    public String customerLoginToken(CustomerSignUpForm form) {
        // 1 로그인 가능여부
        Customer customer = finalValidCustomer(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));
        // 2 토큰 발행


        // 3. 토큰을 리스판스한다.

        return provider.createToken(customer.getEmail(), customer.getId(), UserType.CUSTOMER);
    }

    private Optional<Customer> finalValidCustomer(String email, String password) {
        return customerRepository.findByEmail(email)
                .stream().filter(customer ->
//                        customer.isVerify() &&
                                customer.getPassword().equals(password))
                .findFirst();
    }
}
