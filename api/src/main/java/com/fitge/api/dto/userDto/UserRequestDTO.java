package com.fitge.api.dto.userDto;

import java.time.LocalDate;

import com.fitge.api.dto.mediaDto.MediaRequestDTO;
import com.fitge.api.exception.exceptionCode.UserError;
import com.fitge.api.util.constraint.UserConstraints;
import com.fitge.api.util.validator.MinAge;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDTO {
    @NotBlank(message = UserError.USER_EMPTY_NAME_ERROR)
    @Size(max = UserConstraints.NAME_MAX_LENGTH, message = UserError.INVALID_USER_NAME_ERROR)
    @Pattern(regexp = UserConstraints.NAME_REGEX, message = UserError.INVALID_USER_NAME_ERROR)
    private String name;
    
    @NotBlank(message = UserError.USER_EMPTY_NICKNAME_ERROR)
    @Size(min = UserConstraints.NICKNAME_MIN_LENGTH, max = UserConstraints.NICKNAME_MAX_LENGTH, message = UserError.INVALID_USER_NICKNAME_ERROR)
    @Pattern(regexp = UserConstraints.NICKNAME_REGEX, message = UserError.INVALID_USER_NICKNAME_ERROR)
    private String nickname;

    @NotBlank(message = UserError.USER_EMPTY_EMAIL_ERROR)
    @Email(message = UserError.INVALID_USER_EMAIL_ERROR)
    private String email;

    @NotBlank(message = UserError.USER_EMPTY_PASSWORD_ERROR)
    @Size(min = UserConstraints.PASSWORD_MIN_LENGTH, max = UserConstraints.PASSWORD_MAX_LENGTH, message = UserError.INVALID_USER_PASSWORD_ERROR)
    @Pattern(regexp = UserConstraints.PASSWORD_REGEX, message = UserError.INVALID_USER_PASSWORD_ERROR)
    private String password;

    @NotNull(message = UserError.USER_EMPTY_IS_DARK_THEME_ERROR)
    private Boolean isDarkTheme;

    @NotNull(message = UserError.USER_EMPTY_IS_COMPLAINTER_ANONYMOUS_ERROR)
    private Boolean isComplainterAnonymous;

    @NotNull(message = UserError.USER_EMPTY_IS_RATER_ANONYMOUS_ERROR)
    private Boolean isRaterAnonymous;

    @NotNull(message = UserError.USER_EMPTY_EMAIL_MESSAGES_NOTIFICATION_PERMISSION_ERROR)
    private Boolean emailMessagesNotificationPermission;
    
    @NotNull(message = UserError.USER_EMPTY_EMAIL_NEWS_NOTIFICATION_PERMISSION_ERROR)
    private Boolean emailNewsNotificationPermission;
    
    @NotNull(message = UserError.USER_EMPTY_EMAIL_OTHERS_NOTIFICATION_PERMISSION_ERROR)
    private Boolean emailOthersNotificationPermission;
    
    @NotNull(message = UserError.USER_EMPTY_APP_MESSAGES_NOTIFICATION_PERMISSION_ERROR)
    private Boolean appMessagesNotificationPermission;
    
    @NotNull(message = UserError.USER_EMPTY_APP_NEWS_NOTIFICATION_PERMISSION_ERROR)
    private Boolean appNewsNotificationPermission;

    @NotNull(message = UserError.USER_EMPTY_APP_OTHERS_NOTIFICATION_PERMISSION_ERROR)
    private Boolean appOthersNotificationPermission;

    private Boolean isMale;

    @NotNull(message = UserError.USER_EMPTY_BIRTHDATE_ERROR)
    @MinAge(value = UserConstraints.MIN_AGE, message = UserError.INVALID_USER_AGE_ERROR)
    private LocalDate birthDate;

    private MediaRequestDTO media;
    
    private Short countryId;

    @NotNull(message = UserError.USER_EMPTY_LANGUAGE_ERROR)
    private Byte languageId;

}
