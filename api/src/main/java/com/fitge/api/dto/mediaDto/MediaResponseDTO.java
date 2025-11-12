package com.fitge.api.dto.mediaDto;

import com.fitge.api.model.main.Media;
import lombok.Data;

@Data
public class MediaResponseDTO {
    private Long id;
    private String url;

    public MediaResponseDTO(Media media) {
        this.id = media.getId();
        this.url = media.getUrl();
    }

}
