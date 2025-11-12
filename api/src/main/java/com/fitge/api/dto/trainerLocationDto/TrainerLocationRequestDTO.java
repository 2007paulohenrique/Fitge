package com.fitge.api.dto.trainerLocationDto;

import com.fitge.api.exception.exceptionCode.TrainerLocationError;
import com.fitge.api.util.constraint.TrainerLocationConstraints;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TrainerLocationRequestDTO {
    @NotBlank(message = TrainerLocationError.TRAINER_LOCATION_EMPTY_NAME_ERROR)
    @Size(max = TrainerLocationConstraints.NAME_MAX_LENGTH, message = TrainerLocationError.INVALID_TRAINER_LOCATION_NAME_ERROR)
    @Pattern(regexp = TrainerLocationConstraints.NAME_REGEX, message = TrainerLocationError.INVALID_TRAINER_LOCATION_NAME_ERROR)
    private String name;

    @NotBlank(message = TrainerLocationError.TRAINER_LOCATION_EMPTY_ADDRESS_ERROR)
    @Size(max = TrainerLocationConstraints.ADDRESS_MAX_LENGTH, message = TrainerLocationError.INVALID_TRAINER_LOCATION_ADDRESS_ERROR)
    @Pattern(regexp = TrainerLocationConstraints.ADDRESS_REGEX, message = TrainerLocationError.INVALID_TRAINER_LOCATION_ADDRESS_ERROR)
    private String address;

}
