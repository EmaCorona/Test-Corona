package it.corona.geckosoft.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageResponseDTO {

    @JsonProperty
    private String fileName;

    @JsonProperty
    private byte[] imagesData;
}
