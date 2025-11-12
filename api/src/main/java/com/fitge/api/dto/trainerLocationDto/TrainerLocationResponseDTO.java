package com.fitge.api.dto.trainerLocationDto;

import com.fitge.api.model.main.TrainerLocation;
import lombok.Data;

@Data
public class TrainerLocationResponseDTO {
    private Long id;
    private String name;
    private String address;

    public TrainerLocationResponseDTO(TrainerLocation trainerLocation) {
        this.id = trainerLocation.getId();
        this.name = trainerLocation.getName();
        this.address = trainerLocation.getAddress();
    }

}
