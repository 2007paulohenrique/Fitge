package com.fitge.api.dto.countryDto;

import com.fitge.api.model.main.Country;
import lombok.Data;

@Data
public class CountryResponseDTO {
    private Short id;    
    private String code;

    public CountryResponseDTO(Country country) {
        this.id = country.getId();
        this.code = country.getCode();
    }
    
}
