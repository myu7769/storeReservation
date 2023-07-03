package com.store.storereservation.domain.store;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class reviewReservationForm {

    private String customerEmail;
    private LocalDateTime reservedTime;
    private String storeName;
    private Long point;
    private String comment;

}
