package com.mc.icmc.repository;

import com.mc.icmc.domain.Nationalite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INationaliteRepository extends JpaRepository<Nationalite, Long> {
}