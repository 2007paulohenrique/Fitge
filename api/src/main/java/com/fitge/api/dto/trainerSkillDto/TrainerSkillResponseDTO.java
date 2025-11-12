package com.fitge.api.dto.trainerSkillDto;

import com.fitge.api.dto.trainingTargetDto.TrainingTargetResponseDTO;
import com.fitge.api.model.main.TrainerSkill;

import lombok.Data;

@Data
public class TrainerSkillResponseDTO {
    private Long id;
    private boolean isMain;
    private TrainingTargetResponseDTO trainingTarget;
    
    public TrainerSkillResponseDTO(TrainerSkill trainerSkill) {
        this.id = trainerSkill.getId();
        this.isMain = trainerSkill.getIsMain();
        this.trainingTarget = new TrainingTargetResponseDTO(trainerSkill.getTrainingTarget());
    }
}
