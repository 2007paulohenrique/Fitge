package com.fitge.api.config;

import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
@Validated
public class JwtProperties {
    @NotBlank
    private String secret;

    private SecretKey jwtSecretKey;

    @Min(1)
    private long accessExpirationMs;

    @Min(1)
    private long refreshExpirationMs;
    
    @Min(1)
    private long loginExpirationMs;

    @Min(1)
    private long signUpExpirationMs;

    @NotBlank
    private String accessCookieName;

    @NotBlank
    private String refreshCookieName;
    
    @NotBlank
    private String loginCookieName;

    @NotBlank
    private String signUpCookieName;

    @NotBlank
    private String accessCookiePath;

    @NotBlank
    private String refreshCookiePath;
    
    @NotBlank
    private String loginCookiePath;

    @NotBlank
    private String signUpCookiePath;

    @NotNull
    private boolean cookieHttpOnly;
    
    @NotNull
    private boolean cookieSecure;

    @NotBlank
    private String cookieSameSite;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secret);

        this.jwtSecretKey = Keys.hmacShaKeyFor(keyBytes);
    }

}
