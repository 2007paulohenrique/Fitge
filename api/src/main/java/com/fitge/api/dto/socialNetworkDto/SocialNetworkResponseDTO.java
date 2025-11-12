package com.fitge.api.dto.socialNetworkDto;

import com.fitge.api.model.main.SocialNetwork;
import lombok.Data;

@Data
public class SocialNetworkResponseDTO {
    private Byte id;
    private String name;
    private String webDomain;

    public SocialNetworkResponseDTO(SocialNetwork socialNetwork) {
        this.id = socialNetwork.getId();
        this.name = socialNetwork.getName();
        this.webDomain = socialNetwork.getWebDomain();
    }

}
