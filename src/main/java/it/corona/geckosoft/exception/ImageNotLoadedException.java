package it.corona.geckosoft.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ImageNotLoadedException extends GeckosoftException {
    private static final String IMAGES_NOT_LOADED_EXCEPTION = "Image not loaded in the request.";

    public ImageNotLoadedException() {
        super(IMAGES_NOT_LOADED_EXCEPTION);
    }

    public ImageNotLoadedException(String message) {
        super(message);
    }
}
