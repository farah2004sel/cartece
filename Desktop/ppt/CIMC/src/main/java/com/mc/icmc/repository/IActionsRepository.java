package com.mc.icmc.repository;

import com.mc.icmc.domain.Actions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository Spring Data JPA pour l’entité Actions.
 *
 * @author
 * Ghazi Ben Yahya
 */
@Repository
public interface IActionsRepository extends JpaRepository<Actions, Long> {

    //  Vérifier si une action existe déjà par son titre (utile pour éviter les doublons)
    boolean existsByActionTitle(String actionTitle);

    //  Trouver une action par son actionTitle
    Optional<Actions> findByActionTitle(String actionTitle);
}
