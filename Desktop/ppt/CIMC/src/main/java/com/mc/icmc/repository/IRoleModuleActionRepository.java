package com.mc.icmc.repository;

import com.mc.icmc.domain.RoleModuleAction;
import com.mc.icmc.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleModuleActionRepository extends JpaRepository<RoleModuleAction, Long> {
    void deleteByRole(Roles role);
}
