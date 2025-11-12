package com.fitge.api.dto.clientDto;

import com.fitge.api.dto.userDto.UserRequestDTO;
import com.fitge.api.exception.exceptionCode.ClientError;
import com.fitge.api.util.constraint.ClientConstraints;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientRequestDTO {
    @Min(value = ClientConstraints.MIN_HEIGHT, message = ClientError.INVALID_CLIENT_HEIGHT_ERROR)
    @Max(value = ClientConstraints.MAX_HEIGHT, message = ClientError.INVALID_CLIENT_HEIGHT_ERROR)
    private Short heightCm;

    @Min(value = ClientConstraints.MIN_WEIGHT, message = ClientError.INVALID_CLIENT_WEIGHT_ERROR)
    @Max(value = ClientConstraints.MAX_WEIGHT, message = ClientError.INVALID_CLIENT_WEIGHT_ERROR)
    private Short weightKg;

    @Min(value = 1, message = ClientError.INVALID_CLIENT_WEEK_AVAILABLE_DAYS_ERROR)
    @Max(value = 7, message = ClientError.INVALID_CLIENT_WEEK_AVAILABLE_DAYS_ERROR)
    private Byte weekAvailableDays;

    @Min(value = 1, message = ClientError.INVALID_CLIENT_DAY_AVAILABLE_HOURS_ERROR)
    @Max(value = 24, message = ClientError.INVALID_CLIENT_DAY_AVAILABLE_HOURS_ERROR)
    private Byte dayAvailableHours;

    @Min(value = ClientConstraints.MIN_DAILY_CALORIC_INTAKE_KCAL, message = ClientError.INVALID_CLIENT_DAILY_CALORIC_INTAKE_ERROR)
    @Max(value = ClientConstraints.MAX_DAILY_CALORIC_INTAKE_KCAL, message = ClientError.INVALID_CLIENT_DAILY_CALORIC_INTAKE_ERROR)
    private Short dailyCaloricIntakeKcal;

    @Min(value = ClientConstraints.MIN_DAILY_WATER_INTAKE_LITERS, message = ClientError.INVALID_CLIENT_DAILY_WATER_INTAKE_ERROR)
    @Max(value = ClientConstraints.MAX_DAILY_WATER_INTAKE_LITERS, message = ClientError.INVALID_CLIENT_DAILY_WATER_INTAKE_ERROR)
    private Byte dailyWaterIntakeLiters;

    @Min(value = ClientConstraints.MIN_DAILY_SLEEP_HOURS, message = ClientError.INVALID_CLIENT_DAILY_SLEEP_HOURS_ERROR)
    @Max(value = ClientConstraints.MAX_DAILY_SLEEP_HOURS, message = ClientError.INVALID_CLIENT_DAILY_SLEEP_HOURS_ERROR)
    private Byte dailySleepHours;

    @Size(max = ClientConstraints.CARDIAC_CONDITIONS_MAX_LENGTH, message = ClientError.INVALID_CLIENT_CARDIAC_CONDITIONS_ERROR)
    @Pattern(regexp = ClientConstraints.CARDIAC_CONDITIONS_REGEX, message = ClientError.INVALID_CLIENT_CARDIAC_CONDITIONS_ERROR)
    private String cardiacConditions;

    @Size(max = ClientConstraints.MENTAL_CONDITIONS_MAX_LENGTH, message = ClientError.INVALID_CLIENT_MENTAL_CONDITIONS_ERROR)
    @Pattern(regexp = ClientConstraints.MENTAL_CONDITIONS_REGEX, message = ClientError.INVALID_CLIENT_MENTAL_CONDITIONS_ERROR)
    private String mentalConditions;

    @Size(max = ClientConstraints.PHYSICAL_LIMITATIONS_MAX_LENGTH, message = ClientError.INVALID_CLIENT_PHYSICAL_LIMITATIONS_ERROR)
    @Pattern(regexp = ClientConstraints.PHYSICAL_LIMITATIONS_REGEX, message = ClientError.INVALID_CLIENT_PHYSICAL_LIMITATIONS_ERROR)
    private String physicalLimitations;

    private Byte trainingExperienceId;
    private Byte trainingTargetId;

    @NotNull(message = ClientError.CLIENT_EMPTY_USER_ERROR)
    private UserRequestDTO user;

}
