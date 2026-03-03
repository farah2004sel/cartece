package com.example.cartecom.repository;

import com.example.cartecom.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Users, Long> {

    // Recherche par email
    Optional<Users> findByUserEmail(String email);

    // Recherche par username
    Optional<Users> findByUserName(String userName);

    // Recherche par code verification
    Optional<Users> findByUserVerificationCode(String code);

    // Vérification unicité
    boolean existsByUserEmail(String email);
    boolean existsByUserName(String userName);

     @Query("SELECT u FROM Users u " +
            "WHERE LOWER(u.userName) = LOWER(:val) " +
            "OR LOWER(u.userEmail) = LOWER(:val)")
    Optional<Users> findByUserNameOrUserEmailIgnoreCase(@Param("val") String value);

    // Utilisateurs actifs
    List<Users> findByUserEnabledTrueOrderByIdDesc();

    // Utilisateurs actifs et non annulés
    List<Users> findByUserEnabledTrueAndUserCancelFalseOrderByIdDesc();

    // Compter actifs
    long countByUserEnabledTrue();

    // Recherche par téléphone
    Optional<Users> findByTelephone(String telephone);
    Optional<Users> findByUserNameIgnoreCaseOrUserEmailIgnoreCase(String userName, String userEmail);
}