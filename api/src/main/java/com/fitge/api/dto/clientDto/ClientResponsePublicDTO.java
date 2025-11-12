package com.fitge.api.dto.clientDto;

import com.fitge.api.dto.trainingExperienceDto.TrainingExperienceResponseDTO;
import com.fitge.api.dto.trainingTargetDto.TrainingTargetResponseDTO;
import com.fitge.api.dto.userDto.UserResponsePublicDTO;
import com.fitge.api.model.main.Client;
import lombok.Data;

@Data
public class ClientResponsePublicDTO {
    private Long id;
    private short heightCm;
    private short weightKg;
    private Byte weekAvailableDays;
    private Byte dayAvailableHours;
    private Short dailyCaloricIntakeKcal;
    private Byte dailyWaterIntakeLiters;
    private Byte dailySleepHours;
    private String cardiacConditions;
    private String mentalConditions;
    private String physicalLimitations;
    private TrainingExperienceResponseDTO trainingExperience;
    private TrainingTargetResponseDTO trainingTarget;
    private UserResponsePublicDTO user;

    public ClientResponsePublicDTO(
        Client client, 
        String decryptedCardiacConditions, 
        String decryptedMentalConditions, 
        String decryptedPhysicalLimitations
    ) {
        this.id = client.getId();
        this.heightCm = client.getHeightCm();
        this.weightKg = client.getWeightKg();
        this.weekAvailableDays = client.getWeekAvailableDays();
        this.dayAvailableHours = client.getDayAvailableHours();
        this.dailyCaloricIntakeKcal = client.getDailyCaloricIntakeKcal();
        this.dailyWaterIntakeLiters = client.getDailyWaterIntakeLiters();
        this.dailySleepHours = client.getDailySleepHours();
        this.cardiacConditions = decryptedCardiacConditions;
        this.mentalConditions = decryptedMentalConditions;
        this.physicalLimitations = decryptedPhysicalLimitations;
        this.trainingExperience = client.getTrainingExperience() != null ? new TrainingExperienceResponseDTO(client.getTrainingExperience()) : null;
        this.trainingTarget = client.getTrainingTarget() != null ? new TrainingTargetResponseDTO(client.getTrainingTarget()) : null;
        this.user = new UserResponsePublicDTO(client.getUser());
    }

}
