package com.fitge.api.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fitge.api.dto.mediaDto.MediaRequestDTO;
import com.fitge.api.dto.mediaDto.MediaResponseDTO;
import com.fitge.api.exception.ApiException;
import com.fitge.api.exception.exceptionCode.MediaError;
import com.fitge.api.model.main.Media;
import com.fitge.api.repository.main.MediaRepository;
import com.fitge.api.util.externalService.CloudinaryService;
import com.fitge.api.util.validator.MediaValidator;

@Service
public class MediaService {
    private final MediaRepository mediaRepository;
    private final MediaTypeService mediaTypeService;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public MediaService(MediaRepository mediaRepository, MediaTypeService mediaTypeService, CloudinaryService cloudinaryService) {
        this.mediaRepository = mediaRepository;
        this.mediaTypeService = mediaTypeService;
        this.cloudinaryService = cloudinaryService;
    }

    public Media findById(Long id) {
        return mediaRepository.findById(id)
            .orElseThrow(() -> new ApiException(MediaError.MEDIA_NOT_FOUND_ERROR, HttpStatus.NOT_FOUND));
    }

    public MediaResponseDTO findByIdAndCreateResponseDto(Long id) {
        Media media = findById(id);

        return new MediaResponseDTO(media); 
    }

    public Media createImage(MediaRequestDTO media, Byte maxSizeMb) {
        MediaValidator.validateFileSize(media.getFile(), maxSizeMb);
        MediaValidator.validateMimeType(media.getFile(), "image");

        Map<String, String> uploadResult = cloudinaryService.uploadImage(media.getFile());
        String url = uploadResult.get("url");
        String publicId = uploadResult.get("publicId");
        String mediaType = uploadResult.get("mediaType");

        try {
            Media newMedia = new Media();

            newMedia.setUrl(url);
            newMedia.setCloudinaryPublicId(publicId);
            setMediaType(newMedia, mediaType);
            
            return mediaRepository.save(newMedia);
            
        } catch (Exception e) {
            cloudinaryService.deleteFile(publicId, mediaType);

            throw new RuntimeException("Erro ao criar mídia", e);
        }
    }

    private void setMediaType(Media media, String type) {
        media.setMediaType(mediaTypeService.findByType(type));
    }

    public void delete(Long id) {
        Media media = findById(id);

        cloudinaryService.deleteFile(media.getCloudinaryPublicId(), media.getMediaType().getType());
        mediaRepository.delete(media);
    }
 
}
