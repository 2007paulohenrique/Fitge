package com.fitge.api.dto.trainingTargetDto;

import com.fitge.api.model.main.TrainingTarget;

import lombok.Data;

@Data
public class TrainingTargetResponseDTO {
    private Byte id;
    private String code;
    private Boolean isSport;

    public TrainingTargetResponseDTO(TrainingTarget trainingTarget) {
        this.id = trainingTarget.getId();
        this.code = trainingTarget.getCode();
        this.isSport = trainingTarget.getIsSport();
    }

}
