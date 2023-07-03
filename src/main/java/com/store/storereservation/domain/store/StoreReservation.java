package com.store.storereservation.domain.store;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.store.storereservation.domain.config.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "storeReservation")
public class StoreReservation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    @JsonBackReference
    private Store store;
    private String email;
    private LocalDateTime reservedTime;
    private boolean arrivedCustomer;
    private boolean allowedPartner;
    private boolean endService;

    public static StoreReservation from(StoreReservationRegisterForm form) {
        return StoreReservation.builder()
                .email(form.getCustomerEmail())
                .reservedTime(form.getReservedTime())
                .arrivedCustomer(false)
                .allowedPartner(false)
                .endService(false)
                .build();
    }
    public void setStore(Store store) {
        if (this.store != null) {
            this.store.getReservations().remove(this);
        }

        this.store = store;

        // 양방향 연관관계 설정 시 무한 루프에 빠지지 않게 하는 로직
        if (store != null && !store.getReservations().contains(this)) {
            store.getReservations().add(this);
        }
    }
}
