package com.fitge.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fitge.api.dto.login.LoginRequestDTO;
import com.fitge.api.dto.login.LoginResponseDTO;
import com.fitge.api.dto.signUpDto.SignUpRequestDTO;
import com.fitge.api.dto.signUpDto.SignUpResponseDTO;
import com.fitge.api.exception.ApiException;
import com.fitge.api.exception.exceptionCode.SecurityError;
import com.fitge.api.model.main.Client;
import com.fitge.api.model.main.Trainer;
import com.fitge.api.model.main.User;
import com.fitge.api.util.externalService.JwtService;
import com.fitge.api.util.security.Encrypt;

@Service
public class SecurityService {
    private final UserService userService;
    private final TrainerService trainerService;
    private final ClientService clientService;
    private final JwtService jwtService;

    @Autowired
    public SecurityService(UserService userService, JwtService jwtService, TrainerService trainerService, ClientService clientService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.trainerService = trainerService;
        this.clientService = clientService;
    }

    public LoginResponseDTO checkLogin(LoginRequestDTO login) {
        String hashedEmail = Encrypt.hashSensitiveData(login.getEmail());

        User user = userService.findByEmailHash(hashedEmail)
            .orElseThrow(() -> new ApiException(SecurityError.LOGIN_ERROR, HttpStatus.UNAUTHORIZED));

        if (!Encrypt.checkPassword(login.getPassword(), user.getPasswordHash())) throw new ApiException(SecurityError.LOGIN_ERROR, HttpStatus.UNAUTHORIZED);

        String token;
        
        if (user.getIsTrainer()) {
            Trainer trainer = trainerService.findByUserId(user.getId());

            token = jwtService.generateLoginToken(user.getId(), true, trainer.getId());
        } else {
            Client client = clientService.findByUserId(user.getId());

            token = jwtService.generateLoginToken(user.getId(), false, client.getId());
        }

        return new LoginResponseDTO(token);
    }

    public SignUpResponseDTO checkSignUp(SignUpRequestDTO signUp) {
        String hashedEmail = Encrypt.hashSensitiveData(signUp.getEmail());

        userService.checkEmailAndNicknameIsAvailable(hashedEmail, signUp.getNickname());

        String token = jwtService.generateSignUpToken();

        return new SignUpResponseDTO(token, hashedEmail);
    }
}
