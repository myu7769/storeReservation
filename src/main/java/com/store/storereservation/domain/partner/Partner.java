package com.store.storereservation.domain.partner;

import com.store.storereservation.domain.config.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Partner extends BaseEntity {
    @Id
    @Column(name ="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String name;
    private String password;

    @OneToMany
    @JoinColumn(name= "store_id")
    private List<Store> stores;

    private LocalDateTime verifyExpiredAt;
    private String verificationCode;
    private boolean verify;
}
