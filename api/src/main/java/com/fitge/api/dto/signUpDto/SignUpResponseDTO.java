package com.fitge.api.dto.signUpDto;

import lombok.Data;

@Data
public class SignUpResponseDTO {
    private String token;
    private String emailHash;
    
    public SignUpResponseDTO(String token, String emailHash) {
        this.token = token;
        this.emailHash = emailHash;
    }

}
