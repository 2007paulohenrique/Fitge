package com.fitge.api.util.validator;

import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import com.fitge.api.exception.ApiException;
import com.fitge.api.exception.exceptionCode.MediaError;

public class MediaValidator {
    public static void validateFileSize(MultipartFile file, Byte maxFileSizeMb) {
        if (file.getSize() > maxFileSizeMb * 1024 * 1024) throw new ApiException(MediaError.MEDIA_MAX_SIZE_ERROR, HttpStatus.BAD_REQUEST);
    }

    public static void validateMimeType(MultipartFile file, String allowedType) {
        String fileType = file.getContentType();

        if (fileType == null) throw new ApiException(MediaError.MEDIA_TYPE_ERROR, HttpStatus.BAD_REQUEST);

        switch (allowedType.toLowerCase()) {
            case "image":
                if (!fileType.startsWith("image/")) throw new ApiException(MediaError.MEDIA_TYPE_ERROR, HttpStatus.BAD_REQUEST);
                
                break;

            case "video":
                if (!fileType.startsWith("video/")) throw new ApiException(MediaError.MEDIA_TYPE_ERROR, HttpStatus.BAD_REQUEST);
                
                break;

            case "pdf":
                if (!fileType.equals("application/pdf")) throw new ApiException(MediaError.MEDIA_TYPE_ERROR, HttpStatus.BAD_REQUEST);
                
                break;

            default:
                throw new ApiException(MediaError.MEDIA_TYPE_ERROR, HttpStatus.BAD_REQUEST);
        }
    }

}
