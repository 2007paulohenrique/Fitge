package com.fitge.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fitge.api.dto.languageDto.LanguageResponseDTO;
import com.fitge.api.exception.ApiException;
import com.fitge.api.exception.exceptionCode.LanguageError;
import com.fitge.api.model.main.Language;
import com.fitge.api.repository.main.LanguageRepository;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public Language findById(Byte id) {
        return languageRepository.findById(id)
            .orElseThrow(() -> new ApiException(LanguageError.LANGUAGE_NOT_FOUND_ERROR, HttpStatus.NOT_FOUND));
    }
    
    public LanguageResponseDTO findByIdAndCreateResponseDto(Byte id) {
        Language language = findById(id);

        return new LanguageResponseDTO(language); 
    }
}
