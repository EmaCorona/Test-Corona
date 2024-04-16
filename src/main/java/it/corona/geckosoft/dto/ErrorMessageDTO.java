package it.corona.geckosoft.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorMessageDTO {

    @JsonProperty
    private int status;

    @JsonProperty
    private String error;

    @JsonProperty
    private String description;

    @JsonProperty
    private Date timestamp;
}
