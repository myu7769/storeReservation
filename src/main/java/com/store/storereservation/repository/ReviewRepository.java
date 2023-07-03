package com.store.storereservation.repository;

import com.store.storereservation.domain.Review;
import com.store.storereservation.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByEmail(String email);

}
