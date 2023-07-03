package com.store.storereservation.service;


import com.store.storereservation.common.UserType;
import com.store.storereservation.config.JwtAuthenticationProvider;
import com.store.storereservation.domain.customer.CustomerSignUpForm;
import com.store.storereservation.domain.partner.Partner;
import com.store.storereservation.domain.partner.PartnerSignUpForm;
import com.store.storereservation.domain.partner.ReqInformationStoreForm;
import com.store.storereservation.domain.store.StoreReservation;
import com.store.storereservation.exception.CustomException;
import com.store.storereservation.exception.ErrorCode;
import com.store.storereservation.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final PartnerRepository partnerRepository;

    public Partner partnerSignUp(CustomerSignUpForm form) {

        if (partnerRepository.findByEmail(form.getEmail().toLowerCase(Locale.ROOT)).isPresent()) {
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        }else{
            LocalDateTime now = LocalDateTime.now();
            return partnerRepository.save(Partner.from(form));
        }

    }

    public Optional<Partner> partnerSignIn(String email, String password) {
            return partnerRepository.findByEmail(email)
                    .stream().filter(partner ->
//                            customer.isVerify() &&
                            partner.getPassword().equals(password))
                    .findFirst();
    }
}
