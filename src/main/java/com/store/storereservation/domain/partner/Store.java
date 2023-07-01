package com.store.storereservation.domain.partner;

import com.store.storereservation.domain.config.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = " store_id")
    private Long id;

    private String location;

    private String description;

}
