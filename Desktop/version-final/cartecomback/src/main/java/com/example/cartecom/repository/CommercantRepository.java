package com.example.cartecom.repository;

import com.example.cartecom.entity.Commercant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository

public interface CommercantRepository extends JpaRepository<Commercant, Long> {
    Optional<Commercant> findByEmail(String email);
}