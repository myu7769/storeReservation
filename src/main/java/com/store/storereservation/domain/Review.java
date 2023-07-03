package com.store.storereservation.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.store.storereservation.domain.config.BaseEntity;
import com.store.storereservation.domain.customer.CustomerSignUpForm;
import com.store.storereservation.domain.store.Store;
import lombok.*;

import javax.persistence.*;
import java.util.Locale;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseEntity {
    @Id
    @Column(name ="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private Long point;
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    @JsonBackReference
    private Store store;

//    public static Review from(reviewForm form) {
//        return Review.builder()
//                .email(form.getEmail().toLowerCase(Locale.ROOT))
//                .point(form.getPassword())
//                .description(form.getName())
//                .build();
//    }
}
