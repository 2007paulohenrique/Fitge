package com.fitge.api.config;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

@Data
@Configuration
@ConfigurationProperties(prefix = "encrypt")
@Validated
public class EncryptProperties {
    @NotBlank
    private String secretKey;
    
    private SecretKey aesSecretKey;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);

        this.aesSecretKey = new SecretKeySpec(keyBytes, "AES");
    }

}
