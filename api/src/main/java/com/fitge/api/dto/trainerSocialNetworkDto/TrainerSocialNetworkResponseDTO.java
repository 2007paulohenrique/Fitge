package com.fitge.api.dto.trainerSocialNetworkDto;

import com.fitge.api.dto.socialNetworkDto.SocialNetworkResponseDTO;
import com.fitge.api.model.main.TrainerSocialNetwork;
import lombok.Data;

@Data
public class TrainerSocialNetworkResponseDTO {
    private Long id;
    private String profile;
    private SocialNetworkResponseDTO socialNetwork;

    public TrainerSocialNetworkResponseDTO(TrainerSocialNetwork trainerSocialNetwork) {
        this.id = trainerSocialNetwork.getId();
        this.profile = trainerSocialNetwork.getProfile();
        this.socialNetwork = new SocialNetworkResponseDTO(trainerSocialNetwork.getSocialNetwork());
    }

}
