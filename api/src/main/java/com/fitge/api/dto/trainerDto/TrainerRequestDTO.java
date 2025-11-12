package com.fitge.api.dto.trainerDto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.URL;

import com.fitge.api.dto.trainerLocationDto.TrainerLocationRequestDTO;
import com.fitge.api.dto.trainerSkillDto.TrainerSkillRequestDTO;
import com.fitge.api.dto.trainerSocialNetworkDto.TrainerSocialNetworkRequestDTO;
import com.fitge.api.dto.userDto.UserRequestDTO;
import com.fitge.api.exception.exceptionCode.TrainerError;
import com.fitge.api.util.constraint.TrainerConstraints;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TrainerRequestDTO {
    @Size(max = TrainerConstraints.DESCRIPTION_MAX_LENGTH, message = TrainerError.INVALID_TRAINER_DESCRIPTION_ERROR)
    private String description;
  
    @NotBlank(message = TrainerError.TRAINER_EMPTY_DAYS_FOR_CLIENT_CONTRACT_AFTER_PERMISSION_ERROR)
    @Min(value = TrainerConstraints.MIN_DAYS_FOR_CLIENT_CONTRACT_AFTER_PERMISSION, message = TrainerError.INVALID_TRAINER_DAYS_FOR_CLIENT_CONTRACT_AFTER_PERMISSION_ERROR)
    @Max(value = TrainerConstraints.MAX_DAYS_FOR_CLIENT_CONTRACT_AFTER_PERMISSION, message = TrainerError.INVALID_TRAINER_DAYS_FOR_CLIENT_CONTRACT_AFTER_PERMISSION_ERROR)
    private Byte daysForClientContractAfterPermission;

    @NotBlank(message = TrainerError.TRAINER_EMPTY_MAX_ACTIVE_CONTRACTS_ERROR)
    @Min(value = TrainerConstraints.MIN_MAX_ACTIVE_CONTRACTS, message = TrainerError.INVALID_TRAINER_MAX_ACTIVE_CONTRACTS_ERROR)
    @Max(value = TrainerConstraints.MAX_MAX_ACTIVE_CONTRACTS, message = TrainerError.INVALID_TRAINER_MAX_ACTIVE_CONTRACTS_ERROR)
    private Byte maxActiveContracts;

    @NotNull(message = TrainerError.TRAINER_EMPTY_IS_REQUEST_BLOCKED_IN_UNAVAILABILITY_ERROR)
    private Boolean isRequestsBlockedInUnavailability;

    @NotNull(message = TrainerError.TRAINER_EMPTY_EMAIL_CLIENT_CHANGES_NOTIFICATION_PERMISSION_ERROR)
    private Boolean emailClientChangesNotificationPermission;
    
    @NotNull(message = TrainerError.TRAINER_EMPTY_APP_CLIENT_CHANGES_NOTIFICATION_PERMISSION_ERROR)
    private Boolean appClientChangesNotificationPermission;
        
    @Size(max = 255, message = TrainerError.INVALID_TRAINER_WEBSITE_ERROR)
    @URL(message = TrainerError.INVALID_TRAINER_WEBSITE_ERROR)
    private String website;

    @NotNull(message = TrainerError.TRAINER_EMPTY_USER_ERROR)
    private UserRequestDTO user;

    private List<TrainerLocationRequestDTO> locations = new ArrayList<>();

    private List<TrainerSocialNetworkRequestDTO> socialNetworks = new ArrayList<>();

    private List<TrainerSkillRequestDTO> trainingTargets = new ArrayList<>();

}
