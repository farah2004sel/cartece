package com.example.cartecom.repository;

import com.example.cartecom.domain.Nationalite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INationaliteRepository extends JpaRepository<Nationalite, Long> {
}