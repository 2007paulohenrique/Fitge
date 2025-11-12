package com.fitge.api.dto.trainerSkillDto;

import com.fitge.api.exception.exceptionCode.TrainerSkillError;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TrainerSkillRequestDTO {
    @NotNull(message = TrainerSkillError.TRAINER_SKILL_EMPTY_IS_MAIN_ERROR)
    private Boolean isMain;
    
    @NotBlank(message = TrainerSkillError.TRAINER_SKILL_EMPTY_TRAINING_TARGET_ERROR)
    private Byte trainingTargetId;
    
}
