package com.fitge.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitge.api.model.main.MediaType;
import com.fitge.api.repository.main.MediaTypeRepository;

@Service
public class MediaTypeService {
    private final MediaTypeRepository mediaTypeRepository;

    @Autowired
    public MediaTypeService(MediaTypeRepository mediaTypeRepository) {
        this.mediaTypeRepository = mediaTypeRepository;
    }

    public MediaType findByType(String type) {
        return mediaTypeRepository.findByType(type)
            .orElseThrow(() -> new RuntimeException("Tipo da mídia não encontrado"));
    }

}
