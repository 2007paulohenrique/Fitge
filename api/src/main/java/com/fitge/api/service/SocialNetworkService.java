package com.fitge.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fitge.api.dto.socialNetworkDto.SocialNetworkResponseDTO;
import com.fitge.api.exception.ApiException;
import com.fitge.api.exception.exceptionCode.SocialNetworkError;
import com.fitge.api.model.main.SocialNetwork;
import com.fitge.api.repository.main.SocialNetworkRepository;

@Service
public class SocialNetworkService {
    SocialNetworkRepository socialNetworkRepository;

    @Autowired
    public SocialNetworkService(SocialNetworkRepository socialNetworkRepository) {
        this.socialNetworkRepository = socialNetworkRepository;
    }

    public SocialNetwork findById(Byte id) {
        return socialNetworkRepository.findById(id)
            .orElseThrow(() -> new ApiException(SocialNetworkError.SOCIAL_NETWORK_NOT_FOUND_ERROR, HttpStatus.NOT_FOUND));
    }

    public SocialNetworkResponseDTO findByIdAndCreateResponseDto(Byte id) {
        SocialNetwork socialNetwork = findById(id);

        return new SocialNetworkResponseDTO(socialNetwork); 
    }
}
