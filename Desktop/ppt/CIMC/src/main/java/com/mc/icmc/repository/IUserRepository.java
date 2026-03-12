package com.mc.icmc.repository;

import com.mc.icmc.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<Users, Long> {


    // userName
    Optional<Users> findByUserName(String userName);

    // userEmail
    Optional<Users> findByUserEmail(String userEmail);

    Boolean existsByUserName(String userName);

    Boolean existsByUserEmail(String userEmail);

    // Récupérer tous les users actifs triés par ordre décroissant
    @Query("SELECT u FROM Users u WHERE u.userCancel = 0 ORDER BY u.userSortOrder DESC")
    List<Users> findAllActiveUsersOrderBySortOrderDesc();

    // Récupérer le maximum du sortOrder
    @Query("SELECT COALESCE(MAX(u.userSortOrder), 0) FROM Users u")
    int findMaxSortOrder();


}

