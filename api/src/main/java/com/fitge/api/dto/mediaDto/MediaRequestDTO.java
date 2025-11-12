package com.fitge.api.dto.mediaDto;

import org.springframework.web.multipart.MultipartFile;

import com.fitge.api.exception.exceptionCode.MediaError;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MediaRequestDTO {
    @NotNull(message = MediaError.EMPTY_MEDIA_FILE_ERROR)
    private MultipartFile file;

}
