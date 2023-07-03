package com.store.storereservation.domain.store;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class confirmReservationForm {

    private String customerEmail;
    private LocalDateTime reservedTime;
    private String storeName;
    private boolean allowed;

}
