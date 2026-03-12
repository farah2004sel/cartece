
package com.mc.icmc.repository;

import com.mc.icmc.domain.Demande;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDemandeRepository extends JpaRepository<Demande, Long> {
    List<Demande> findByCommercantId(Long commercantId);

}
