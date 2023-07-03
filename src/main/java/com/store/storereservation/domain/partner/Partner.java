package com.store.storereservation.domain.partner;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.store.storereservation.domain.config.BaseEntity;
import com.store.storereservation.domain.customer.CustomerSignUpForm;
import com.store.storereservation.domain.store.Store;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "partner")
public class Partner extends BaseEntity {
    @Id
    @Column(name ="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String name;
    private String password;

    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Store> stores = new ArrayList<>();


    public static Partner from(CustomerSignUpForm form) {
        return Partner.builder()
                .email(form.getEmail().toLowerCase(Locale.ROOT))
                .password(form.getPassword())
                .name(form.getName())
                .build();
    }
    public void addStore(Store store) {
        this.stores.add(store);
        store.setPartner(this);
    }
    public void removeStore(Store store) {
        this.stores.remove(store);
        store.setPartner(null);
    }
}
