package com.fitge.api.dto.trainerDto;

import java.util.List;
import com.fitge.api.dto.trainerLocationDto.TrainerLocationResponseDTO;
import com.fitge.api.dto.trainerSocialNetworkDto.TrainerSocialNetworkResponseDTO;
import com.fitge.api.dto.userDto.UserResponsePublicDTO;
import com.fitge.api.model.main.Trainer;
import lombok.Data;

@Data
public class TrainerResponsePublicDTO {
    private Long id;
    private String description;
    private Double rating;
    private int ratingsNumber;
    private int complaintsNumber;
    private int contractsNumber;
    private Double paymentPlansDurationPriceRatio;
    private Double paymentPlansAveragePrice;
    private Byte daysForClientContractAfterPermission;
    private Boolean isAvailable;
    private Boolean isRequestsBlockedInUnavailability;
    private String website;
    private UserResponsePublicDTO user;
    private List<TrainerLocationResponseDTO> locations;
    private List<TrainerSocialNetworkResponseDTO> socialNetworks;

    public TrainerResponsePublicDTO(Trainer trainer) {
        this.id = trainer.getId();
        this.description = trainer.getDescription();
        this.rating = trainer.getRating();
        this.ratingsNumber = trainer.getRatingsNumber();
        this.complaintsNumber = trainer.getComplaintsNumber();
        this.contractsNumber = trainer.getContractsNumber();
        this.paymentPlansDurationPriceRatio = trainer.getPaymentPlansDurationPriceRatio();
        this.paymentPlansAveragePrice = trainer.getPaymentPlansAveragePrice();
        this.daysForClientContractAfterPermission = trainer.getDaysForClientContractAfterPermission();
        this.isAvailable = trainer.getIsAvailable();
        this.isRequestsBlockedInUnavailability = trainer.getIsRequestsBlockedInUnavailability();
        this.website = trainer.getWebsite();
        this.user = new UserResponsePublicDTO(trainer.getUser());
        this.locations = trainer.getLocations().stream().map(TrainerLocationResponseDTO::new).toList();
        this.socialNetworks = trainer.getSocialNetworks().stream().map(TrainerSocialNetworkResponseDTO::new).toList();
    }

}
