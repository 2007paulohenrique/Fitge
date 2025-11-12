package com.fitge.api.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fitge.api.config.EncryptProperties;
import com.fitge.api.dto.userDto.UserRequestDTO;
import com.fitge.api.dto.userDto.UserResponsePrivateDTO;
import com.fitge.api.dto.userDto.UserResponsePublicDTO;
import com.fitge.api.exception.ApiException;
import com.fitge.api.model.main.FitgePlan;
import com.fitge.api.model.main.User;
import com.fitge.api.repository.main.UserRepository;
import com.fitge.api.util.security.Encrypt;
import com.fitge.api.exception.exceptionCode.UserError;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final EncryptProperties encryptProperties;
    private final CountryService countryService;
    private final FitgePlanService fitgePlanService;
    private final LanguageService languageService;

    @Autowired
    public UserService(
        UserRepository userRepository, 
        EncryptProperties encryptProperties, 
        CountryService countryService, 
        FitgePlanService fitgePlanService,
        LanguageService languageService
    ) {
        this.userRepository = userRepository;
        this.encryptProperties = encryptProperties;
        this.countryService = countryService;
        this.fitgePlanService = fitgePlanService;
        this.languageService = languageService;
    }

    public User findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ApiException(UserError.USER_NOT_FOUND_ERROR, HttpStatus.NOT_FOUND));
    }

    public UserResponsePrivateDTO findByIdAndCreateResponsePrivateDto(Long id) {
        User user = findById(id);

        Map<String, String> decryptedData = decryptUserSensitiveData(
            user.getEmailEncrypted()
        );

        FitgePlan fitgePlan = fitgePlanService.findUserFitgePlan(id);

        UserResponsePrivateDTO userDto = new UserResponsePrivateDTO(
            user, 
            decryptedData.get("email"),
            fitgePlan.getLevel()  
        );

        return userDto;
    }

    public UserResponsePublicDTO findByIdAndCreateResponsePublicDto(Long id) {
        User user = findById(id);

        return new UserResponsePublicDTO(user);
    }

    public Optional<User> findByEmailHash(String emailHash) {
        return userRepository.findByEmailHash(emailHash);
    }

    public void checkEmailAndNicknameIsAvailable(String emailHash, String nickname) {
        userRepository.findByEmailHashOrNickname(emailHash, nickname).ifPresent(user -> {
            if (user.getEmailHash().equals(emailHash)) {
                throw new ApiException(UserError.EMAIL_ALREADY_EXISTS_ERROR, HttpStatus.CONFLICT);
            }

            if (user.getNickname().equals(nickname)) {
                throw new ApiException(UserError.NICKNAME_ALREADY_EXISTS_ERROR, HttpStatus.CONFLICT);
            }
        });
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public User create(
        UserRequestDTO user,
        boolean isTrainer
    ) {
        Map<String, byte[]> encryptedDataMap = encryptUserSensitiveData(user.getEmail());
        Map<String, String> hashedDataMap = hashUserSensitiveData(user.getEmail(), user.getPassword());

        checkEmailAndNicknameIsAvailable(hashedDataMap.get("email"), user.getNickname());

        User newUser = new User();

        newUser.setName(user.getName());
        newUser.setNickname(user.getNickname());
        newUser.setEmailEncrypted(encryptedDataMap.get("email"));
        newUser.setEmailHash(hashedDataMap.get("email"));
        newUser.setPasswordHash(hashedDataMap.get("password"));
        newUser.setIsTrainer(isTrainer);
        newUser.setIsDarkTheme(user.getIsDarkTheme());
        newUser.setIsComplainterAnonymous(user.getIsComplainterAnonymous());
        newUser.setIsRaterAnonymous(user.getIsRaterAnonymous());
        newUser.setEmailMessagesNotificationPermission(user.getEmailMessagesNotificationPermission());
        newUser.setEmailNewsNotificationPermission(user.getEmailNewsNotificationPermission());
        newUser.setEmailOthersNotificationPermission(user.getEmailOthersNotificationPermission());
        newUser.setAppMessagesNotificationPermission(user.getAppMessagesNotificationPermission());
        newUser.setAppNewsNotificationPermission(user.getAppNewsNotificationPermission());
        newUser.setAppOthersNotificationPermission(user.getAppOthersNotificationPermission());
        newUser.setIsMale(user.getIsMale());
        newUser.setBirthDate(user.getBirthDate());
        setUserCountry(newUser, user.getCountryId());
        setUserLanguage(newUser, user.getLanguageId());

        return userRepository.save(newUser);
    }

    public void delete(Long id) {
        User user = findById(id);

        userRepository.delete(user);
    }

    private void setUserCountry(User user, Short countryId) {
        if (countryId == null) return;
        
        user.setCountry(countryService.findById(countryId));
    }

    private void setUserLanguage(User user, Byte languageId) {
        user.setLanguage(languageService.findById(languageId));
    }

    public Map<String, String> decryptUserSensitiveData(byte[] encryptedEmail) {
        Map<String, String> decryptedDataMap = new HashMap<>();

        if (encryptedEmail != null) {
            String email = Encrypt.decryptSensitiveData(encryptedEmail, encryptProperties.getAesSecretKey());

            decryptedDataMap.put("email", email);
        }

        return decryptedDataMap;
    }

    private Map<String, byte[]> encryptUserSensitiveData(String email) {
        Map<String, byte[]> encryptedDataMap = new HashMap<>();

        if (email != null) {
            byte[] emailEncrypted = Encrypt.encryptSensitiveData(email, encryptProperties.getAesSecretKey());

            encryptedDataMap.put("email", emailEncrypted);
        }

        return encryptedDataMap;
    }

    private Map<String, String> hashUserSensitiveData(String email, String password) {
        Map<String, String> hashedDataMap = new HashMap<>();

        if (email != null) {
            String emailHash = Encrypt.hashSensitiveData(email);

            hashedDataMap.put("email", emailHash);
        }
        
        if (password != null) {
            String passwordHash = Encrypt.hashPassword(password);

            hashedDataMap.put("password", passwordHash);
        }

        return hashedDataMap;
    }
}
