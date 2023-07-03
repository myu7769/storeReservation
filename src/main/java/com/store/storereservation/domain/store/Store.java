package com.store.storereservation.domain.store;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.store.storereservation.domain.Review;
import com.store.storereservation.domain.config.BaseEntity;
import com.store.storereservation.domain.partner.Partner;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "store")
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id")
    @JsonBackReference
    private Partner partner;

    @Column(unique = true)
    private String name;
    private String location;
    private String description;

    @OneToMany(mappedBy = "store",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "store",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<StoreReservation> reservations = new ArrayList<>();

    public static Store from(StoreRegisterForm form) {
        return Store.builder()
                .name(form.getName())
                .location(form.getLocation())
                .description(form.getDescription())
                .build();
    }
    public void setPartner(Partner partner) {
        if (this.partner != null) {
            this.partner.getStores().remove(this);
        }

        this.partner = partner;

        // 양방향 연관관계 설정 시 무한 루프에 빠지지 않게 하는 로직
        if (partner != null && !partner.getStores().contains(this)) {
            partner.getStores().add(this);
        }
    }
    public void removeStoreReservation(StoreReservation storeReservation) {
        this.reservations.remove(storeReservation);
        storeReservation.setStore(null);
    }
}
