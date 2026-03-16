package com.mc.icmc.repository;

import com.mc.icmc.domain.Commercant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface ICommercantRepository extends JpaRepository<Commercant, Long> {
    Optional<Commercant> findByUser_Id(Long userId);
}
