package com.example.cartecom.repository;

import com.example.cartecom.domain.Actions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActionsRepository extends JpaRepository<Actions, Long> {
}