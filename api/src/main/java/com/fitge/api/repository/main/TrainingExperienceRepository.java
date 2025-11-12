package com.fitge.api.repository.main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitge.api.model.main.TrainingExperience;

@Repository
public interface TrainingExperienceRepository extends JpaRepository<TrainingExperience, Byte> {
    
}
