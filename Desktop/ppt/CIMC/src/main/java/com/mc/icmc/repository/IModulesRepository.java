package com.mc.icmc.repository;

import com.mc.icmc.domain.Modules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IModulesRepository extends JpaRepository<Modules, Long> {


    // Récupérer toutes les Modules triées par ordre décroissant selon ModuleSortOrder
    List<Modules> findAllByOrderByModuleSortOrderDesc();

    @Query("SELECT COALESCE(MAX(e.moduleSortOrder), 0) FROM Modules e")
    int findMaxModuleSortOrder();
}
