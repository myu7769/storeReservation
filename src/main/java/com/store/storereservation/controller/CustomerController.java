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

    /**
     * Customer 회원가입 method
     *
     * @param form
     * @return
     */
    @PostMapping("/signUp")
    public ResponseEntity<Customer> customerSignUp(@RequestBody CustomerSignUpForm form) {
        return ResponseEntity.ok(customerService.customerSignUp(form));
    }

    /**
     * email, password를 post로 받아 로그인 성공 여부를 체크함
     *
     * @param email
     * @param password
     * @return
     */
    @PostMapping("/signIn")
    public ResponseEntity<String> customerSignIn(String email, String password) {
        Customer customer = customerService.customerSignIn(email, password)
                .orElseThrow(()-> new CustomException(LOGIN_CHECK_FAIL));

        return ResponseEntity.ok("Login Success!");
    }

    /**
     * 등록되어 있는 store에 customer가 테이블을 예약하는 method
     * @param form
     * @return
     */

    @PostMapping("/storeReservedRegister")
    public ResponseEntity<StoreReservation> storeReservationRegister(@RequestBody StoreReservationRegisterForm form) {
        return ResponseEntity.ok(storeReservationService.storeReservationRegister(form));
    }

    /**
     * 예약한 매장 테이블을 삭제하는 method
     *
     * @param form
     * @return
     */

    @DeleteMapping("/storeReservedDelete")
    public ResponseEntity<String> storeReservationCancel(@RequestBody StoreReservationRegisterForm form) {
        return ResponseEntity.ok(storeReservationService.storeReservationCancel(form));
    }

    /**
     * partner가 등록한 store를 customer에게
     * pageable을 사용해 일정 개수만 보여주는 method
     *
     * @param pageable
     * @return
     */
    @GetMapping("/ReqStoreInformation")
    public ResponseEntity<?> ReqStoreInformation(final Pageable pageable) {
        Page<Store> storePage = storeService.getAllStore(pageable);
        return ResponseEntity.ok(storePage);
    }

    /**
     * email과 store의 이름을 사용해 매장 도착 후
     * 예약 정보를 확인하는 method
     *
     * @param email
     * @param storeName
     * @return
     */

    @PostMapping("/confirmReservation")
    public ResponseEntity<StoreReservation> confirmReservation(String email,String storeName) {
        return ResponseEntity.ok(storeReservationService.confirmCustomerReservation(email,storeName));
    }

    /**
     * 예약 정보를 확인 후 매장 도착 정보를 서버에 알리는 method
     *  테이블 예약 정보에 t/f로 전달해줘 도착했음을 알림
     *
     * @param form
     * @return
     */
    @PostMapping("/arrivedStore")
    public ResponseEntity<StoreReservation> arrivedStore(@RequestBody confirmReservationForm form) {
        return ResponseEntity.ok(storeReservationService.arrivedStore(form));
    }

    /**
     * 매장 테이블 사용 완료 후
     * customer가 리뷰를 남기는 method
     *
     * @param form
     * @return
     */

    @PostMapping("/reviewReservation")
    public ResponseEntity<Store> reviewReservation(@RequestBody reviewReservationForm form) {
        return ResponseEntity.ok(storeReservationService.reviewReservation(form));
    }

}
