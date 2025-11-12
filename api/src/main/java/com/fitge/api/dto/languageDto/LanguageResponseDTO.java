package com.fitge.api.dto.languageDto;

import com.fitge.api.model.main.Language;

import lombok.Data;

@Data
public class LanguageResponseDTO {
    private Byte id;
    private String code;

    public LanguageResponseDTO(Language language) {
        this.id = language.getId();
        this.code = language.getCode();
    }
}
