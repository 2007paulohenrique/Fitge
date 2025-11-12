package com.fitge.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fitge.api.dto.countryDto.CountryResponseDTO;
import com.fitge.api.exception.ApiException;
import com.fitge.api.exception.exceptionCode.CountryError;
import com.fitge.api.model.main.Country;
import com.fitge.api.repository.main.CountryRepository;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
    
    public Country findById(Short id) {
        return countryRepository.findById(id)
            .orElseThrow(() -> new ApiException(CountryError.COUNTRY_NOT_FOUND_ERROR, HttpStatus.NOT_FOUND));
    }

    public CountryResponseDTO findByIdAndCreateResponseDto(Short id) {
        Country country = findById(id);

        return new CountryResponseDTO(country); 
    }

}
