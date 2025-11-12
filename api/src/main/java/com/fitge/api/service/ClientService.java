package com.fitge.api.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fitge.api.config.EncryptProperties;
import com.fitge.api.dto.clientDto.ClientRequestDTO;
import com.fitge.api.dto.clientDto.ClientResponsePrivateDTO;
import com.fitge.api.dto.clientDto.ClientResponsePublicDTO;
import com.fitge.api.dto.mediaDto.MediaRequestDTO;
import com.fitge.api.exception.ApiException;
import com.fitge.api.exception.exceptionCode.ClientError;
import com.fitge.api.model.main.Client;
import com.fitge.api.model.main.FitgePlan;
import com.fitge.api.model.main.User;
import com.fitge.api.repository.main.ClientRepository;
import com.fitge.api.util.constraint.UserConstraints;
import com.fitge.api.util.security.Encrypt;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserService userService;
    private final MediaService mediaService;
    private final TrainingExperienceService trainingExperienceService;
    private final TrainingTargetService trainingTargetService;
    private final EncryptProperties encryptProperties;
    private final FitgePlanService fitgePlanService;

    @Autowired
    public ClientService(
        ClientRepository clientRepository, 
        EncryptProperties encryptProperties, 
        UserService userService, 
        MediaService mediaService,
        TrainingExperienceService trainingExperienceService,
        TrainingTargetService trainingTargetService,
        FitgePlanService fitgePlanService
    ) {
        this.clientRepository = clientRepository;
        this.encryptProperties = encryptProperties;
        this.userService = userService;
        this.mediaService = mediaService;
        this.trainingExperienceService = trainingExperienceService;
        this.trainingTargetService = trainingTargetService;
        this.fitgePlanService = fitgePlanService;
    }

    public Client findById(Long id) {
        return clientRepository.findById(id)
            .orElseThrow(() -> new ApiException(ClientError.CLIENT_NOT_FOUND_ERROR, HttpStatus.NOT_FOUND));
    }

    public Client findByUserId(Long userId) {
        return clientRepository.findByUser_id(userId)
            .orElseThrow(() -> new ApiException(ClientError.CLIENT_NOT_FOUND_ERROR, HttpStatus.NOT_FOUND));
    }
    
    public ClientResponsePrivateDTO findByIdAndCreateResponsePrivateDto(Long id) {
        Client client = findById(id);

        Map<String, String> decryptedData = decryptClientSensitiveData(
            client.getCardiacConditions(), 
            client.getPhysicalLimitations(), 
            client.getMentalConditions()
        );
        Map<String, String> decryptedUserData = userService.decryptUserSensitiveData(client.getUser().getEmailEncrypted());

        FitgePlan fitgePlan = fitgePlanService.findUserFitgePlan(client.getUser().getId());

        ClientResponsePrivateDTO clientDto = new ClientResponsePrivateDTO(
            client, 
            decryptedUserData.get("email"),
            decryptedData.get("cardiacConditions"),
            decryptedData.get("mentalConditions"), 
            decryptedData.get("physicalLimitations"),
            fitgePlan.getLevel()
        );

        return clientDto;
    }

    public ClientResponsePublicDTO findByIdAndCreateResponsePublicDto(Long id) {
        Client client = findById(id);

        Map<String, String> decryptedData = decryptClientSensitiveData(
            client.getCardiacConditions(), 
            client.getPhysicalLimitations(), 
            client.getMentalConditions()
        );

        ClientResponsePublicDTO clientDto = new ClientResponsePublicDTO(
            client, 
            decryptedData.get("cardiacConditions"),
            decryptedData.get("mentalConditions"), 
            decryptedData.get("physicalLimitations")
        );

        return clientDto;
    }

    @Transactional
    public Client create(
        ClientRequestDTO client
    ) {
        Map<String, byte[]> encryptedDataMap = encryptClientSensitiveData(client.getCardiacConditions(), client.getPhysicalLimitations(), client.getMentalConditions());

        User newUser = userService.create(client.getUser(), false);

        Client newClient = new Client();

        newClient.setHeightCm(client.getHeightCm());
        newClient.setWeightKg(client.getWeightKg());
        newClient.setWeekAvailableDays(client.getWeekAvailableDays());
        newClient.setDayAvailableHours(client.getDayAvailableHours());
        newClient.setDailyCaloricIntakeKcal(client.getDailyCaloricIntakeKcal());
        newClient.setDailyWaterIntakeLiters(client.getDailyWaterIntakeLiters());
        newClient.setDailySleepHours(client.getDailySleepHours());
        newClient.setCardiacConditions(encryptedDataMap.get("cardiacConditions"));
        newClient.setMentalConditions(encryptedDataMap.get("mentalConditions"));
        newClient.setPhysicalLimitations(encryptedDataMap.get("physicalLimitations"));
        setClientTrainingExperience(newClient, client.getTrainingExperienceId());
        setClientTrainingTarget(newClient, client.getTrainingTargetId());
        newClient.setUser(newUser);
        setClientUserMedia(newUser, client.getUser().getMedia());

        return clientRepository.save(newClient);
    }

    private void setClientTrainingExperience(Client client, Byte trainingExperienceId) {
        if (trainingExperienceId == null) return;

        client.setTrainingExperience(trainingExperienceService.findById(trainingExperienceId));
    }

    private void setClientTrainingTarget(Client client, Byte trainingTargetId) {
        if (trainingTargetId == null) return;

        client.setTrainingTarget(trainingTargetService.findById(trainingTargetId));
    }

    private void setClientUserMedia(User user, MediaRequestDTO media) {
        if (media == null) return;

        user.setMedia(mediaService.createImage(media, UserConstraints.MAX_PHOTO_SIZE_MB));
    }

    private Map<String, byte[]> encryptClientSensitiveData(String cardiacConditions, String physicalLimitations, String mentalConditions) {
        Map<String, byte[]> encryptedDataMap = new HashMap<>();

        if (cardiacConditions != null) {
            byte[] encryptedCardiacConditions = Encrypt.encryptSensitiveData(cardiacConditions, encryptProperties.getAesSecretKey());

            encryptedDataMap.put("cardiacConditions", encryptedCardiacConditions);
        }

        if (mentalConditions != null) {
            byte[] encryptedMentalConditions = Encrypt.encryptSensitiveData(mentalConditions, encryptProperties.getAesSecretKey());

            encryptedDataMap.put("mentalConditions", encryptedMentalConditions);
        }

        if (physicalLimitations != null) {
            byte[] encryptedPhysicalLimitations = Encrypt.encryptSensitiveData(physicalLimitations, encryptProperties.getAesSecretKey());

            encryptedDataMap.put("physicalLimitations", encryptedPhysicalLimitations);
        }

        return encryptedDataMap;
    }

    private Map<String, String> decryptClientSensitiveData(byte[] encryptedCardiacConditions, byte[] encryptedPhysicalLimitations, byte[] encryptedMentalConditions) {
        Map<String, String> decryptedDataMap = new HashMap<>();

        if (encryptedCardiacConditions != null) {
            String cardiacConditions = Encrypt.decryptSensitiveData(encryptedCardiacConditions, encryptProperties.getAesSecretKey());

            decryptedDataMap.put("cardiacConditions", cardiacConditions);
        }

        if (encryptedMentalConditions != null) {
            String mentalConditions = Encrypt.decryptSensitiveData(encryptedMentalConditions, encryptProperties.getAesSecretKey());

            decryptedDataMap.put("mentalConditions", mentalConditions);
        }

        if (encryptedPhysicalLimitations != null) {
            String physicalLimitations = Encrypt.decryptSensitiveData(encryptedPhysicalLimitations, encryptProperties.getAesSecretKey());

            decryptedDataMap.put("physicalLimitations", physicalLimitations);
        }

        return decryptedDataMap;
    }

    public void delete(Long id) {
        Client client = findById(id);

        clientRepository.delete(client);
    }
}
