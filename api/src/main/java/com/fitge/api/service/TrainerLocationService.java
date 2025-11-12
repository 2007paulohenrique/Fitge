package com.fitge.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitge.api.dto.trainerLocationDto.TrainerLocationRequestDTO;
import com.fitge.api.model.main.Trainer;
import com.fitge.api.model.main.TrainerLocation;
import com.fitge.api.repository.main.TrainerLocationRepository;

@Service
public class TrainerLocationService {
    TrainerLocationRepository trainerLocationRepository;

    @Autowired
    public TrainerLocationService(TrainerLocationRepository trainerLocationRepository) {
        this.trainerLocationRepository = trainerLocationRepository;
    }

    public List<TrainerLocation> createAll(List<TrainerLocationRequestDTO> trainerLocations, Trainer trainer) {
        if (trainerLocations == null || trainerLocations.isEmpty()) return new ArrayList<>();

        List<TrainerLocation> newTrainerLocations = trainerLocations.stream()
            .map(dto -> {
                TrainerLocation newTrainerLocation = new TrainerLocation();

                newTrainerLocation.setAddress(dto.getAddress());
                newTrainerLocation.setName(dto.getName());
                newTrainerLocation.setTrainer(trainer);

                return newTrainerLocation;
            })
            .collect(Collectors.toList());

        return trainerLocationRepository.saveAll(newTrainerLocations);
    }
}
