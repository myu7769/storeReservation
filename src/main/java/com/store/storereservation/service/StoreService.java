package com.store.storereservation.service;


import com.store.storereservation.common.UserType;
import com.store.storereservation.config.JwtAuthenticationProvider;
import com.store.storereservation.domain.partner.Partner;
import com.store.storereservation.domain.partner.PartnerSignUpForm;
import com.store.storereservation.domain.store.Store;
import com.store.storereservation.domain.store.StoreDeleteForm;
import com.store.storereservation.domain.store.StoreRegisterForm;
import com.store.storereservation.exception.CustomException;
import com.store.storereservation.exception.ErrorCode;
import com.store.storereservation.repository.PartnerRepository;
import com.store.storereservation.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Optional;

import static com.store.storereservation.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final PartnerRepository partnerRepository;
    private final JwtAuthenticationProvider provider;

    @Transactional
    public Store storeRegister(StoreRegisterForm form) {

        Partner partner = partnerRepository.findByEmail(form.getPartnerId().toLowerCase(Locale.ROOT))
                .orElseThrow(()-> new CustomException(NOT_FOUND_USER));

        if(storeRepository.findByName(form.getName()).isPresent()){
            throw new CustomException(ALREADY_NAME_STORE);
        }
        Store store = Store.from(form);
        store.setPartner(partner);
        partner.getStores().add(store);

        return store;
    }

    @Transactional
    public String storeDelete(StoreDeleteForm form) {

        Partner partner = partnerRepository.findByEmail(form.getPartnerId().toLowerCase(Locale.ROOT))
                .orElseThrow(()-> new CustomException(NOT_FOUND_USER));

        Store store = storeRepository.findByName((form.getName()))
                .orElseThrow(()-> new CustomException(NOT_FOUND_STORE));

        partner.removeStore(store);
        storeRepository.delete(store);

        return "Delete Success!";
    }



    public Optional<Partner> customerSignIn(String email, String password) {
            return partnerRepository.findByEmail(email)
                    .stream().filter(partner ->
//                            customer.isVerify() &&
                            partner.getPassword().equals(password))
                    .findFirst();
    }




    public String customerLoginToken(PartnerSignUpForm form) {
        // 1 로그인 가능여부
        Partner partner = finalValidCustomer(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));
        // 2 토큰 발행


        // 3. 토큰을 리스판스한다.

        return provider.createToken(partner.getEmail(), partner.getId(), UserType.PARTNER);
    }

    private Optional<Partner> finalValidCustomer(String email, String password) {
        return partnerRepository.findByEmail(email)
                .stream().filter(partner ->
//                        customer.isVerify() &&
                        partner.getPassword().equals(password))
                .findFirst();
    }

    public Page<Store> getAllStore(Pageable pageable) {
        return storeRepository.findAll(pageable);
    }
}
