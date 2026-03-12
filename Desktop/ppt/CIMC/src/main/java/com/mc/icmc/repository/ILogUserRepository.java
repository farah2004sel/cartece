package com.mc.icmc.repository;

import com.mc.icmc.domain.Log_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ILogUserRepository extends JpaRepository<Log_User, Long> {

    // Optionnel : récupérer un log par son UUID
    boolean existsByLogUserUuid(UUID uuid);

    Log_User findByLogUserUuid(UUID uuid);
}
