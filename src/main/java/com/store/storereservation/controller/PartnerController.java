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

    @PostMapping("/signUp")
    public ResponseEntity<Partner> partnerSignUp(@RequestBody CustomerSignUpForm form) {
        return ResponseEntity.ok(partnerService.partnerSignUp(form));
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> partnerSignIn(String email, String password) {
        Partner partner = partnerService.partnerSignIn(email, password)
                .orElseThrow(()-> new CustomException(LOGIN_CHECK_FAIL));

        return ResponseEntity.ok("Login Success!");
    }

    @PostMapping("/storeInformation")
    public ResponseEntity<List<StoreReservation>> partnerReqInformationStore(@RequestBody ReqInformationStoreForm form) {
        return ResponseEntity.ok(storeReservationService.ReqInformationStore(form));
    }
    @PostMapping("/confirmReservation")
    public ResponseEntity<StoreReservation> confirmReservation(@RequestBody confirmReservationForm form) {
        return ResponseEntity.ok(storeReservationService.confirmPartnerReservation(form));
    }


    @PostMapping("/endReservation")
    public ResponseEntity<StoreReservation> endReservation(@RequestBody confirmReservationForm form) {
        return ResponseEntity.ok(storeReservationService.endReservation(form));
    }
}
