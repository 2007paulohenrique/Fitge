package com.fitge.api.dto.trainingExperienceDto;

import com.fitge.api.model.main.TrainingExperience;
import lombok.Data;

@Data
public class TrainingExperienceResponseDTO {
    private Byte id;
    private String code;
    private Byte minExperienceYears; 
    private Byte maxExperienceYears;

    public TrainingExperienceResponseDTO(TrainingExperience trainingExperience) {
        this.id = trainingExperience.getId();
        this.code = trainingExperience.getCode();
        this.minExperienceYears = trainingExperience.getMinExperienceYears();
        this.maxExperienceYears = trainingExperience.getMaxExperienceYears();
    }
    
}
