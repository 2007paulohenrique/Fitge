package com.fitge.api.dto.login;

import com.fitge.api.exception.exceptionCode.SecurityError;
import com.fitge.api.exception.exceptionCode.UserError;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @NotBlank(message = UserError.USER_EMPTY_EMAIL_ERROR)
    @Email(message = SecurityError.LOGIN_ERROR)
    private String email;

    @NotBlank(message = UserError.USER_EMPTY_PASSWORD_ERROR)
    private String password;

}
