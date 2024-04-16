package it.corona.geckosoft.exception;

import it.corona.geckosoft.dto.ErrorMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ImageNotLoadedException.class)
    public ResponseEntity<ErrorMessageDTO> handleImageNotLoadedException(ImageNotLoadedException ex) {
        return getErrorMessage(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(ImageProcessingException.class)
    public ResponseEntity<ErrorMessageDTO> handleImageProcessingException(ImageProcessingException ex) {
        return getErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @ExceptionHandler(GeckosoftException.class)
    public ResponseEntity<ErrorMessageDTO> handleGenericException(GeckosoftException ex) {
        return getErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    private ResponseEntity<ErrorMessageDTO> getErrorMessage(HttpStatus status, GeckosoftException ex) {
        ErrorMessageDTO errorMessage = new ErrorMessageDTO();
        errorMessage.setStatus(status.value());
        errorMessage.setError(status.getReasonPhrase());
        errorMessage.setTimestamp(new Date());
        errorMessage.setDescription(ex.getLocalizedMessage());
        return ResponseEntity.status(status.value()).body(errorMessage);
    }
}
