package com.store.storereservation.service;


import com.store.storereservation.domain.customer.Customer;
import com.store.storereservation.domain.customer.CustomerSignUpForm;
import com.store.storereservation.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer signUp(CustomerSignUpForm form) {
        return customerRepository.save(Customer.from(form));
    }


}
