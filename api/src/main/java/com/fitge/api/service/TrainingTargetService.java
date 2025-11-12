package com.fitge.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fitge.api.dto.trainingTargetDto.TrainingTargetResponseDTO;
import com.fitge.api.exception.ApiException;
import com.fitge.api.exception.exceptionCode.TrainingTargetError;
import com.fitge.api.model.main.TrainingTarget;
import com.fitge.api.repository.main.TrainingTargetRepository;

@Service
public class TrainingTargetService {
    TrainingTargetRepository trainingTargetRepository;

    @Autowired
    public TrainingTargetService(TrainingTargetRepository trainingTargetRepository) {
        this.trainingTargetRepository = trainingTargetRepository;
    }

    public TrainingTarget findById(Byte id) {
        return trainingTargetRepository.findById(id)
            .orElseThrow(() -> new ApiException(TrainingTargetError.TRAINING_TARGET_NOT_FOUND_ERROR, HttpStatus.NOT_FOUND));
    }

    public TrainingTargetResponseDTO findByIdAndCreateResponseDto(Byte id) {
        TrainingTarget trainingTarget = findById(id);
        
        return new TrainingTargetResponseDTO(trainingTarget);
    }

}