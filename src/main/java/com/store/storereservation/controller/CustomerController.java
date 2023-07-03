package com.store.storereservation.controller;

import com.store.storereservation.domain.customer.Customer;
import com.store.storereservation.domain.customer.CustomerSignUpForm;
import com.store.storereservation.domain.store.*;
import com.store.storereservation.exception.CustomException;
import com.store.storereservation.service.CustomerService;

import com.store.storereservation.service.StoreReservationService;
import com.store.storereservation.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.store.storereservation.exception.ErrorCode.LOGIN_CHECK_FAIL;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final StoreService storeService;
    private final StoreReservationService storeReservationService;

    @PostMapping("/signUp")
    public ResponseEntity<Customer> customerSignUp(@RequestBody CustomerSignUpForm form) {
        return ResponseEntity.ok(customerService.customerSignUp(form));
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> customerSignIn(String email, String password) {
        Customer customer = customerService.customerSignIn(email, password)
                .orElseThrow(()-> new CustomException(LOGIN_CHECK_FAIL));

        return ResponseEntity.ok("Login Success!");
    }

    @PostMapping("/storeReservedRegister")
    public ResponseEntity<StoreReservation> storeReservationRegister(@RequestBody StoreReservationRegisterForm form) {
        return ResponseEntity.ok(storeReservationService.storeReservationRegister(form));
    }
    @DeleteMapping("/storeReservedDelete")
    public ResponseEntity<String> storeReservationCancel(@RequestBody StoreReservationRegisterForm form) {
        return ResponseEntity.ok(storeReservationService.storeReservationCancel(form));
    }
    @GetMapping("/ReqStoreInformation")
    public ResponseEntity<?> ReqStoreInformation(final Pageable pageable) { // final Pageable pageable 을 사용해 프론트에서 요청한 page만 넘겨준다.( 전체 다 넘기면 비효율적)
        Page<Store> storePage = storeService.getAllStore(pageable);
        return ResponseEntity.ok(storePage);
    }

    @PostMapping("/confirmReservation")
    public ResponseEntity<StoreReservation> confirmReservation(String email,String storeName) {
        return ResponseEntity.ok(storeReservationService.confirmCustomerReservation(email,storeName));
    }
    @PostMapping("/arrivedStore")
    public ResponseEntity<StoreReservation> arrivedStore(@RequestBody confirmReservationForm form) {
        return ResponseEntity.ok(storeReservationService.arrivedStore(form));
    }

    @PostMapping("/reviewReservation")
    public ResponseEntity<Store> reviewReservation(@RequestBody reviewReservationForm form) {
        return ResponseEntity.ok(storeReservationService.reviewReservation(form));
    }

}
