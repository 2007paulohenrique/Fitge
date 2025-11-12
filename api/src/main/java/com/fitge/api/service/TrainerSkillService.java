package com.fitge.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitge.api.dto.trainerSkillDto.TrainerSkillRequestDTO;
import com.fitge.api.model.main.Trainer;
import com.fitge.api.model.main.TrainerSkill;
import com.fitge.api.repository.main.TrainerSkillRepository;

@Service
public class TrainerSkillService {
    TrainerSkillRepository trainerSkillRepository;
    TrainingTargetService trainingTargetService;

    @Autowired
    public TrainerSkillService(TrainerSkillRepository trainerSkillRepository, TrainingTargetService trainingTargetService) {
        this.trainerSkillRepository = trainerSkillRepository;
        this.trainingTargetService = trainingTargetService;
    }

    public List<TrainerSkill> createAll(List<TrainerSkillRequestDTO> trainerSkills, Trainer trainer) {
        if (trainerSkills == null || trainerSkills.isEmpty()) return new ArrayList<>();

        List<TrainerSkill> newTrainerSkills = trainerSkills.stream()
            .map(dto -> {
                TrainerSkill newTrainerSkill = new TrainerSkill();

                newTrainerSkill.setTrainingTarget(trainingTargetService.findById(dto.getTrainingTargetId()));
                newTrainerSkill.setIsMain(dto.getIsMain());
                newTrainerSkill.setTrainer(trainer);

                return newTrainerSkill;
            })
            .collect(Collectors.toList());

        return trainerSkillRepository.saveAll(newTrainerSkills);
    }
}
