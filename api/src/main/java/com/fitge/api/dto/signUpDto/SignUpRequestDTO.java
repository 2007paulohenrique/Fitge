package com.fitge.api.dto.signUpDto;

import com.fitge.api.exception.exceptionCode.UserError;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpRequestDTO {
    @NotBlank(message = UserError.USER_EMPTY_NICKNAME_ERROR)
    private String nickname;

    @NotBlank(message = UserError.USER_EMPTY_EMAIL_ERROR)
    @Email(message = UserError.INVALID_USER_EMAIL_ERROR)
    private String email;

}
