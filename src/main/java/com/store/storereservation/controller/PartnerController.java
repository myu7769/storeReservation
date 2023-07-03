package com.store.storereservation.controller;

import com.store.storereservation.domain.customer.CustomerSignUpForm;
import com.store.storereservation.domain.partner.Partner;
import com.store.storereservation.domain.partner.ReqInformationStoreForm;
import com.store.storereservation.domain.store.StoreReservation;
import com.store.storereservation.domain.store.StoreReservationRegisterForm;
import com.store.storereservation.domain.store.confirmReservationForm;
import com.store.storereservation.exception.CustomException;
import com.store.storereservation.service.PartnerService;
import com.store.storereservation.service.StoreReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.store.storereservation.exception.ErrorCode.LOGIN_CHECK_FAIL;

@RestController
@RequiredArgsConstructor
@RequestMapping("/partner")
public class PartnerController {

    private final PartnerService partnerService;
    private final StoreReservationService storeReservationService;

    /**
     * partner 회원 가입
     * @param form
     * @return
     */

    @PostMapping("/signUp")
    public ResponseEntity<Partner> partnerSignUp(@RequestBody CustomerSignUpForm form) {
        return ResponseEntity.ok(partnerService.partnerSignUp(form));
    }

    /**
     * 파트너 로그인
     * @param email
     * @param password
     * @return
     */
    @PostMapping("/signIn")
    public ResponseEntity<String> partnerSignIn(String email, String password) {
        Partner partner = partnerService.partnerSignIn(email, password)
                .orElseThrow(()-> new CustomException(LOGIN_CHECK_FAIL));

        return ResponseEntity.ok("Login Success!");
    }

    /**
     * 예약 승인/거절을 위한 파트너가 자신의 매장에 예약된 정보를 확인할 수 있는 Method
     *
     * @param form
     * @return
     */

    @PostMapping("/storeInformation")
    public ResponseEntity<List<StoreReservation>> partnerReqInformationStore(@RequestBody ReqInformationStoreForm form) {
        return ResponseEntity.ok(storeReservationService.ReqInformationStore(form));
    }

    /**
     * 파트너의 매장에 예약을 승인/거절하는 method
     *
     * @param form
     * @return
     */

    @PostMapping("/confirmReservation")
    public ResponseEntity<StoreReservation> confirmReservation(@RequestBody confirmReservationForm form) {
        return ResponseEntity.ok(storeReservationService.confirmPartnerReservation(form));
    }


    /**
     * 매장 예약 종료되었음을 서버에 알리는 method
     * 정상적으로 종료됐을 경우 review 작성 가능
     *
     * @param form
     * @return
     */

    @PostMapping("/endReservation")
    public ResponseEntity<StoreReservation> endReservation(@RequestBody confirmReservationForm form) {
        return ResponseEntity.ok(storeReservationService.endReservation(form));
    }
}
