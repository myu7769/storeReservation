package com.store.storereservation.repository;

import com.store.storereservation.domain.store.Store;
import com.store.storereservation.domain.store.StoreReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface StoreReservationRepository extends JpaRepository<StoreReservation, Long> {

    Optional<StoreReservation> findByStoreNameAndReservedTime(String storeName,LocalDateTime reservedTime);

    List<StoreReservation> findAllByStoreNameOrderByReservedTime(String storeName);

    Optional<StoreReservation> findByEmailAndStoreName(String email, String storeName);



}
