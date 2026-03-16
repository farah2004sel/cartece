package com.mc.icmc.repository;

import com.mc.icmc.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IRoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByRoleName(String roleName);
    Optional<Roles> findByRoleId(Long roleId);

    // Récupérer toutes les Roles triées par ordre décroissant selon RoleSortOrder
    @Query("""
    SELECT r
    FROM Roles r
    WHERE r.rolePublished = 1
      AND r.roleCancel = 0
    ORDER BY r.roleSortOrder DESC
""")
    List<Roles> findAllPublishedAndActiveOrdered();

    @Query("SELECT COALESCE(MAX(e.roleSortOrder), 0) FROM Roles e")
    int findMaxRoleSortOrder();
}