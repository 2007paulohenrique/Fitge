package com.fitge.api.repository.main;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitge.api.model.main.FitgePlan;

@Repository
public interface FitgePlanRepository extends JpaRepository<FitgePlan, Byte> {
    Optional<FitgePlan> findByLevel(Byte level);
}
