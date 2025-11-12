package com.fitge.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitge.api.dto.trainerSocialNetworkDto.TrainerSocialNetworkRequestDTO;
import com.fitge.api.model.main.Trainer;
import com.fitge.api.model.main.TrainerSocialNetwork;
import com.fitge.api.repository.main.TrainerSocialNetworkRepository;

@Service
public class TrainerSocialNetworkService {
    TrainerSocialNetworkRepository trainerSocialNetworkRepository;
    SocialNetworkService socialNetworkService;

    @Autowired
    public TrainerSocialNetworkService(TrainerSocialNetworkRepository trainerSocialNetworkRepository, SocialNetworkService socialNetworkService) {
        this.trainerSocialNetworkRepository = trainerSocialNetworkRepository;
        this.socialNetworkService = socialNetworkService;
    }

    public List<TrainerSocialNetwork> createAll(List<TrainerSocialNetworkRequestDTO> trainerSocialNetworks, Trainer trainer) {
        if (trainerSocialNetworks == null || trainerSocialNetworks.isEmpty()) return new ArrayList<>();

        List<TrainerSocialNetwork> newTrainerSocialNetworks = trainerSocialNetworks.stream()
            .map(dto -> {
                TrainerSocialNetwork newTrainerSocialNetwork = new TrainerSocialNetwork();

                newTrainerSocialNetwork.setProfile(dto.getProfile());
                newTrainerSocialNetwork.setSocialNetwork(socialNetworkService.findById(dto.getSocialNetworkId()));
                newTrainerSocialNetwork.setTrainer(trainer);

                return newTrainerSocialNetwork;
            })
            .collect(Collectors.toList());

        return trainerSocialNetworkRepository.saveAll(newTrainerSocialNetworks);
    }
}
