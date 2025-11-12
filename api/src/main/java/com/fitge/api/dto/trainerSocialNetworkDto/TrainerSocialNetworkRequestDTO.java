package com.fitge.api.dto.trainerSocialNetworkDto;

import com.fitge.api.exception.exceptionCode.TrainerSocialNetworkError;
import com.fitge.api.util.constraint.TrainerSocialNetworkConstraints;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TrainerSocialNetworkRequestDTO {
    @NotBlank(message = TrainerSocialNetworkError.TRAINER_SOCIAL_NETWORK_EMPTY_NAME_ERROR)
    @Size(max = TrainerSocialNetworkConstraints.PROFILE_MAX_LENGTH, message = TrainerSocialNetworkError.INVALID_TRAINER_SOCIAL_NETWORK_PROFILE_ERROR)
    @Pattern(regexp = TrainerSocialNetworkConstraints.PROFILE_REGEX, message = TrainerSocialNetworkError.INVALID_TRAINER_SOCIAL_NETWORK_PROFILE_ERROR)
    private String profile;
  
    @NotBlank(message = TrainerSocialNetworkError.TRAINER_SOCIAL_NETWORK_EMPTY_SOCIAL_NETWORK_ERROR)
    private Byte socialNetworkId;

}
