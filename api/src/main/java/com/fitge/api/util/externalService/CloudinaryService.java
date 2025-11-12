package com.fitge.api.util.externalService;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fitge.api.config.CloudinaryProperties;
import com.fitge.api.exception.ApiException;
import com.fitge.api.exception.exceptionCode.MediaError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryService(CloudinaryProperties cloudinaryProperties) {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudinaryProperties.getCloudName(),
            "api_key", cloudinaryProperties.getApiKey(),
            "api_secret", cloudinaryProperties.getApiSecret()
        ));
    }

    public Map<String, String> uploadImage(MultipartFile file) {
        if (file == null || file.isEmpty()) throw new IllegalArgumentException("Arquivo de imagem inválido ou vazio");
        
        try { 
            Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "folder", "fitge/users/photos",
                "transformation", new Object[]{
                    ObjectUtils.asMap(
                        "width", 500,
                        "height", 500,
                        "crop", "fill",
                        "gravity", "auto",
                        "fetch_format", "auto",
                        "quality", "auto"
                    )
                },
                "resource_type", "image"
            ));
            
            return formatUploadResult(uploadResult);

        } catch (IOException e) {
            throw new ApiException(MediaError.UPLOAD_FILE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, e);

        }
    }
    
    private Map<String, String> formatUploadResult(Map<?, ?> uploadResult) {
        try { 
            Map<String, String> formattedUploadResult = new HashMap<>();

            formattedUploadResult.put("publicId", uploadResult.get("public_id").toString());
            formattedUploadResult.put("url", uploadResult.get("secure_url").toString());
            formattedUploadResult.put("mediaType", uploadResult.get("resource_type").toString());

            return formattedUploadResult;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao formatar resposta do upload no cloudinary", e);

        }
    }

    public Map<String, String> uploadPdf(MultipartFile file) {
        if (file == null || file.isEmpty()) throw new IllegalArgumentException("Arquivo pdf inválido ou vazio");
        
        try { 
            Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "folder", "fitge/users/docs",
                "resource_type", "raw"
            ));
            
            return formatUploadResult(uploadResult);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao fazer upload do pdf no cloudinary", e);

        }
    }

    public void deleteFile(String publicId, String mediaType) {
        if (publicId == null || publicId.isBlank()) throw new IllegalArgumentException("Id público do arquivo inválido ou vazio");

        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.asMap(
                "resource_type", mediaType
            ));

        } catch (IOException e) {
            throw new RuntimeException("Erro ao excluir arquivo no cloudinary", e);
            
        }
    }

}
