package com.store.storereservation.service;


import com.store.storereservation.domain.Review;
import com.store.storereservation.domain.customer.Customer;
import com.store.storereservation.domain.partner.ReqInformationStoreForm;
import com.store.storereservation.domain.store.*;
import com.store.storereservation.exception.CustomException;
import com.store.storereservation.repository.CustomerRepository;
import com.store.storereservation.repository.StoreRepository;
import com.store.storereservation.repository.StoreReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.time.temporal.ChronoUnit;


import static com.store.storereservation.exception.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreReservationService {

    private final StoreRepository storeRepository;
    private final CustomerRepository customerRepository;
    private final StoreReservationRepository storeReservationRepository;

    @Transactional
    public StoreReservation storeReservationRegister(StoreReservationRegisterForm form) {

        Customer customer = customerRepository.findByEmail(form.getCustomerEmail().toLowerCase(Locale.ROOT))
                .orElseThrow(()-> new CustomException(NOT_FOUND_USER));

        LocalDateTime now = LocalDateTime.now();
        now = now.truncatedTo(ChronoUnit.HOURS).plusMinutes((now.getMinute() / 5) * 5);
        LocalDateTime rounded = form.getReservedTime().truncatedTo(ChronoUnit.HOURS)
                .plusMinutes((form.getReservedTime().getMinute() / 5) * 5);

        if (now.isBefore(rounded)) {
            throw new CustomException(NOT_DateOrTime_INVAILD);
        }
        if (now.isEqual(rounded)) {
            throw new CustomException(ALREADY_CUSTOMER_RESERVATION);
        }


        StoreReservation storeReservation = StoreReservation.builder()
                .email(form.getCustomerEmail())
                .reservedTime(rounded)
                .build();

        Store store = storeRepository.findByName(form.getStoreName())
                        .orElseThrow(()-> new CustomException(NOT_FOUND_STORE));

        storeReservation.setStore(store);
        storeReservation.setStore(store);
        store.getReservations().add(storeReservation);

        return storeReservation;
    }

    @Transactional
    public String storeReservationCancel(StoreReservationRegisterForm form) {

        StoreReservation storeReservation = storeReservationRepository.findByStoreNameAndReservedTime(form.getStoreName(), form.getReservedTime())
                .orElseThrow(()-> new CustomException(NOT_FOUNT_RESERVATION));

        Store store = storeRepository.findByName((form.getStoreName()))
                .orElseThrow(()-> new CustomException(NOT_FOUND_STORE));

        store.removeStoreReservation(storeReservation);
        storeReservationRepository.delete(storeReservation);

        return "Delete Success!";
    }

    public List<StoreReservation> ReqInformationStore(ReqInformationStoreForm form) {


        List<StoreReservation> reservations = storeReservationRepository.findAllByStoreNameOrderByReservedTime(form.getStoreName());
        if (reservations.isEmpty()) {
            throw new CustomException(NOT_FOUNT_RESERVATION);
        }
        return reservations;
    }

    @Transactional
    public StoreReservation confirmPartnerReservation(confirmReservationForm form) {

        StoreReservation storeReservation = storeReservationRepository.findByStoreNameAndReservedTime(form.getStoreName(), form.getReservedTime())
                .orElseThrow(()-> new CustomException(NOT_FOUNT_RESERVATION));

        storeReservation.setAllowedPartner(form.isAllowed());

        return storeReservation;

    }

    @Transactional
    public StoreReservation endReservation(confirmReservationForm form) {
        StoreReservation storeReservation = storeReservationRepository.findByStoreNameAndReservedTime(form.getStoreName(), form.getReservedTime())
                .orElseThrow(()-> new CustomException(NOT_FOUNT_RESERVATION));

        storeReservation.setEndService(form.isAllowed());

        return storeReservation;

    }

    @Transactional
    public StoreReservation arrivedStore(confirmReservationForm form) {
        StoreReservation storeReservation = storeReservationRepository.findByEmailAndStoreName(form.getCustomerEmail(),form.getStoreName())
                .orElseThrow(()-> new CustomException(NOT_FOUNT_RESERVATION));

        if(storeReservation.getReservedTime().minusMinutes(10).isBefore(form.getReservedTime())) {
            throw new CustomException(LATE_RESERVATION);
        }
        storeReservation.setArrivedCustomer(form.isAllowed());

        return storeReservation;
    }

    public StoreReservation confirmCustomerReservation(String email, String storeName) {

        StoreReservation storeReservation = storeReservationRepository.findByEmailAndStoreName(email, storeName)
                .orElseThrow(() -> new CustomException(NOT_FOUNT_RESERVATION));

        return storeReservation;
    }

    public Store reviewReservation(reviewReservationForm form) {

        StoreReservation storeReservation = storeReservationRepository.findByEmailAndStoreName(form.getCustomerEmail(), form.getStoreName())
                .orElseThrow(() -> new CustomException(NOT_FOUNT_RESERVATION));

        if (!storeReservation.isEndService()) {
            throw new CustomException(NOT_FINISHED_SERVICE);
        }

        Store store = storeRepository.findByName(form.getStoreName())
                .orElseThrow(() -> new CustomException(NOT_FOUND_STORE));

        Review review = Review.builder()
                .point(form.getPoint())
                .comment(form.getComment())
                .email(form.getCustomerEmail())
                .build();


        review.setStore(store);
        review.setStore(store);
        store.getReviews().add(review);

        return store;
    }
}
