package com.fitge.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fitge.api.dto.trainingExperienceDto.TrainingExperienceResponseDTO;
import com.fitge.api.exception.ApiException;
import com.fitge.api.exception.exceptionCode.TrainingExperienceError;
import com.fitge.api.model.main.TrainingExperience;
import com.fitge.api.repository.main.TrainingExperienceRepository;

@Service
public class TrainingExperienceService {
    TrainingExperienceRepository trainingExperienceRepository;

    @Autowired
    public TrainingExperienceService(TrainingExperienceRepository trainingExperienceRepository) {
        this.trainingExperienceRepository = trainingExperienceRepository;
    }

    public TrainingExperience findById(Byte id) {
        return trainingExperienceRepository.findById(id)
            .orElseThrow(() -> new ApiException(TrainingExperienceError.TRAINING_EXPERIENCE_NOT_FOUND_ERROR, HttpStatus.NOT_FOUND));
    }

    public TrainingExperienceResponseDTO findByIdAndCreateResponseDto(Byte id) {
        TrainingExperience trainingExperience = findById(id);
        
        return new TrainingExperienceResponseDTO(trainingExperience);
    }
    
}
