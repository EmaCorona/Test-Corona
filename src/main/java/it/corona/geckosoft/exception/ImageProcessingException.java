package it.corona.geckosoft.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ImageProcessingException extends GeckosoftException {
    public static final String IMAGE_PROCESSING_EXCEPTION = "There was a problem during the elaboration of one or more images.";
    public ImageProcessingException() {
        super(IMAGE_PROCESSING_EXCEPTION);
    }
    public ImageProcessingException(String message) {
        super(message);
    }
}
