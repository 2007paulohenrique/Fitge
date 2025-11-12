package com.fitge.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Validated
public class MainDataSourceProperties {
    @NotBlank
    private String url;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
