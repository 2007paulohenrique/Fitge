package com.fitge.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "cloudinary")
@Validated
public class CloudinaryProperties {
    @NotBlank
    private String cloudName;
    
    @NotBlank
    private String apiKey;
    
    @NotBlank
    private String apiSecret;
    
}
