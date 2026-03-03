package com.example.cartecom.repository;

import com.example.cartecom.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Roles, Long> {

    @Query("SELECT COALESCE(MAX(r.roleSortOrder), 0) FROM Roles r")
    int findMaxRoleSortOrder();

    @Query("""
           SELECT r FROM Roles r
           WHERE r.rolePublished = 1
           AND r.roleCancel = 0
           ORDER BY r.roleSortOrder ASC
           """)
    List<Roles> findAllPublishedAndActiveOrdered();

    Optional<Roles> findByRoleName(String roleName);
}