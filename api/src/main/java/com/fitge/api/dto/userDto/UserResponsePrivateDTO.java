package com.fitge.api.dto.userDto;

import java.time.LocalDate;

import com.fitge.api.dto.countryDto.CountryResponseDTO;
import com.fitge.api.dto.languageDto.LanguageResponseDTO;
import com.fitge.api.dto.mediaDto.MediaResponseDTO;
import com.fitge.api.model.main.User;
import lombok.Data;

@Data
public class UserResponsePrivateDTO {    
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private Boolean isDarkTheme;
    private Boolean isComplainterAnonymous;
    private Boolean isRaterAnonymous;
    private Boolean isMale;
    private LocalDate birthDate;
    private Byte fitgePlanLevel;
    private MediaResponseDTO media;
    private CountryResponseDTO country;
    private LanguageResponseDTO language;

    public UserResponsePrivateDTO(User user, String decryptedEmail, Byte fitgePlanLevel) {
        this.id = user.getId();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.email = decryptedEmail;
        this.isDarkTheme = user.getIsDarkTheme();
        this.isComplainterAnonymous = user.getIsComplainterAnonymous();
        this.isRaterAnonymous = user.getIsRaterAnonymous();
        this.isMale = user.getIsMale();
        this.birthDate = user.getBirthDate();
        this.fitgePlanLevel = fitgePlanLevel;
        this.country = user.getCountry() != null ? new CountryResponseDTO(user.getCountry()) : null;
        this.language = new LanguageResponseDTO(user.getLanguage());
        this.media = user.getMedia() != null ? new MediaResponseDTO(user.getMedia()) : null;
    }

}
