package com.fitge.api.repository.main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitge.api.model.main.TrainerLocation;

@Repository
public interface TrainerLocationRepository extends JpaRepository<TrainerLocation, Long> {
    
}
