package com.fitge.api.dto.userDto;

import java.time.LocalDate;

import com.fitge.api.dto.countryDto.CountryResponseDTO;
import com.fitge.api.dto.mediaDto.MediaResponseDTO;
import com.fitge.api.model.main.User;
import lombok.Data;

@Data
public class UserResponsePublicDTO {
    private Long id;
    private String name;
    private String nickname;
    private Boolean isMale;
    private LocalDate birthDate;
    private MediaResponseDTO media;
    private CountryResponseDTO country;

    public UserResponsePublicDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.isMale = user.getIsMale();
        this.birthDate = user.getBirthDate();
        this.country = user.getCountry() != null ? new CountryResponseDTO(user.getCountry()) : null;
        this.media = user.getMedia() != null ? new MediaResponseDTO(user.getMedia()) : null;
    }

}
