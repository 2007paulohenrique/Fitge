package com.fitge.api.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fitge.api.dto.mediaDto.MediaRequestDTO;
import com.fitge.api.dto.trainerDto.TrainerRequestDTO;
import com.fitge.api.dto.trainerDto.TrainerResponsePrivateDTO;
import com.fitge.api.dto.trainerDto.TrainerResponsePublicDTO;
import com.fitge.api.dto.trainerLocationDto.TrainerLocationRequestDTO;
import com.fitge.api.dto.trainerSkillDto.TrainerSkillRequestDTO;
import com.fitge.api.dto.trainerSocialNetworkDto.TrainerSocialNetworkRequestDTO;
import com.fitge.api.exception.ApiException;
import com.fitge.api.exception.exceptionCode.TrainerError;
import com.fitge.api.model.main.FitgePlan;
import com.fitge.api.model.main.Trainer;
import com.fitge.api.model.main.User;
import com.fitge.api.repository.main.TrainerRepository;
import com.fitge.api.util.constraint.UserConstraints;

@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;
    private final UserService userService;
    private final MediaService mediaService;
    private final FitgePlanService fitgePlanService;
    private final TrainerSkillService trainerSkillService;
    private final TrainerSocialNetworkService trainerSocialNetworkService;
    private final TrainerLocationService trainerLocationService;

    @Autowired
    public TrainerService(
        TrainerRepository trainerRepository, 
        UserService userService, 
        MediaService mediaService,
        TrainerSkillService trainerSkillService,
        TrainerSocialNetworkService trainerSocialNetworkService,
        TrainerLocationService trainerLocationService,
        FitgePlanService fitgePlanService
    ) {
        this.trainerRepository = trainerRepository;
        this.userService = userService;
        this.mediaService = mediaService;
        this.trainerSkillService = trainerSkillService;
        this.trainerSocialNetworkService = trainerSocialNetworkService;
        this.trainerLocationService = trainerLocationService;
        this.fitgePlanService = fitgePlanService;
    }

    public Trainer findById(Long id) {
        return trainerRepository.findById(id)
            .orElseThrow(() -> new ApiException(TrainerError.TRAINER_NOT_FOUND_ERROR, HttpStatus.NOT_FOUND));
    }

    public Trainer findByUserId(Long userId) {
        return trainerRepository.findByUser_id(userId)
            .orElseThrow(() -> new ApiException(TrainerError.TRAINER_NOT_FOUND_ERROR, HttpStatus.NOT_FOUND));
    }
    
    public TrainerResponsePrivateDTO findByIdAndCreateResponsePrivateDto(Long id) {
        Trainer trainer = findById(id);

        Map<String, String> decryptedUserData = userService.decryptUserSensitiveData(trainer.getUser().getEmailEncrypted());

        FitgePlan fitgePlan = fitgePlanService.findUserFitgePlan(trainer.getUser().getId());

        TrainerResponsePrivateDTO trainerDto = new TrainerResponsePrivateDTO(
            trainer, 
            decryptedUserData.get("email"),
            fitgePlan.getLevel()
        );

        return trainerDto;
    }

    public TrainerResponsePublicDTO findByIdAndCreateResponsePublicDto(Long id) {
        TrainerResponsePublicDTO trainerDto = new TrainerResponsePublicDTO(findById(id));

        return trainerDto;
    }

    @Transactional
    public Trainer create(
        TrainerRequestDTO trainer
    ) {
        User newUser = userService.create(trainer.getUser(), true);

        Trainer newTrainer = new Trainer();

        newTrainer.setDescription(trainer.getDescription());
        newTrainer.setDaysForClientContractAfterPermission(trainer.getDaysForClientContractAfterPermission());
        newTrainer.setMaxActiveContracts(trainer.getMaxActiveContracts());
        newTrainer.setIsRequestsBlockedInUnavailability(trainer.getIsRequestsBlockedInUnavailability());
        newTrainer.setEmailClientChangesNotificationPermission(trainer.getEmailClientChangesNotificationPermission());
        newTrainer.setAppClientChangesNotificationPermission(trainer.getAppClientChangesNotificationPermission());
        newTrainer.setWebsite(trainer.getWebsite());
        newTrainer.setUser(newUser);
        setTrainerUserMedia(newUser, trainer.getUser().getMedia());

        Trainer savedTrainer = trainerRepository.save(newTrainer);

        setTrainerLocations(savedTrainer, trainer.getLocations());
        setTrainerSkills(savedTrainer, trainer.getTrainingTargets());
        setTrainerSocialNetworks(savedTrainer, trainer.getSocialNetworks());

        return trainerRepository.save(savedTrainer);
    }

    private void setTrainerLocations(Trainer trainer, List<TrainerLocationRequestDTO> trainerLocations) {
        trainer.setLocations(trainerLocationService.createAll(trainerLocations, trainer));
    }
    
    private void setTrainerSocialNetworks(Trainer trainer, List<TrainerSocialNetworkRequestDTO> trainerSocialNetworks) {
        trainer.setSocialNetworks(trainerSocialNetworkService.createAll(trainerSocialNetworks, trainer));
    }
    
    private void setTrainerSkills(Trainer trainer, List<TrainerSkillRequestDTO> trainerSkills) {
        trainer.setTrainingTargets(trainerSkillService.createAll(trainerSkills, trainer));
    }

    private void setTrainerUserMedia(User user, MediaRequestDTO media) {
        if (media == null) return;

        user.setMedia(mediaService.createImage(media, UserConstraints.MAX_PHOTO_SIZE_MB));
    }

    public void delete(Long id) {
        Trainer trainer = findById(id);

        trainerRepository.delete(trainer);
    }

}
