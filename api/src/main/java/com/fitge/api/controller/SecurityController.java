package com.fitge.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fitge.api.dto.login.LoginRequestDTO;
import com.fitge.api.dto.login.LoginResponseDTO;
import com.fitge.api.dto.signUpDto.SignUpRequestDTO;
import com.fitge.api.dto.signUpDto.SignUpResponseDTO;
import com.fitge.api.service.SecurityService;
import com.fitge.api.util.externalService.CookieService;
import com.fitge.api.util.externalService.RedisService;
import com.fitge.api.util.formatter.ApiResponseFormatter;
import com.fitge.api.util.translation.ApiSuccessResponseCodeMessage;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@Validated
public class SecurityController {
    private final SecurityService securityService;
    private final RedisService redisService;
    private final CookieService cookieService;

    @Autowired
    public SecurityController(SecurityService securityService, RedisService redisService, CookieService cookieService) {
        this.securityService = securityService;
        this.redisService = redisService;
        this.cookieService = cookieService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<ApiResponseFormatter<LoginResponseDTO>> postLogin(
        @Valid @RequestBody LoginRequestDTO loginDto,
        HttpServletResponse response
    ) {
        LoginResponseDTO login = securityService.checkLogin(loginDto);

        cookieService.addLoginCookie(response, login.getToken());

        return ResponseEntity.ok(ApiResponseFormatter.of(null, ApiSuccessResponseCodeMessage.POST_LOGIN_SUCCESS));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponseFormatter<SignUpResponseDTO>> postSignUp(
        @Valid @RequestBody SignUpRequestDTO signUpDto,
        HttpServletResponse response
    ) {
        SignUpResponseDTO signUp = securityService.checkSignUp(signUpDto);

        redisService.saveValueWithoutExpiration(signUp.getToken(), signUp.getEmailHash());

        cookieService.addSignUpCookie(response, signUp.getToken());

        return ResponseEntity.ok(ApiResponseFormatter.of(null, ApiSuccessResponseCodeMessage.POST_SIGN_UP_SUCCESS));
    }

}
