package t.it.simplespringapp.commons.errorhandlers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import t.it.simplespringapp.commons.exceptions.ApiException;
import t.it.simplespringapp.interfaces.models.responses.WebResponse;

@Slf4j
@RestControllerAdvice
public class RestErrorHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<WebResponse<String>> constraintViolationException(ConstraintViolationException constraintViolationException) {
        log.warn("Error", constraintViolationException);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(WebResponse.<String>builder().errors(String.join(", ", constraintViolationException.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList())).build());
    }

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ResponseEntity<WebResponse<String>> apiException(ApiException apiException) {
        log.warn("Error", apiException);
        final var responseException = mappedToResponseStatusException(apiException);
        return ResponseEntity.status(responseException.getStatusCode())
                .body(WebResponse.<String>builder().errors(responseException.getReason()).build());
    }

    private static ResponseStatusException mappedToResponseStatusException(ApiException apiException) {
        return new ResponseStatusException(switch (apiException.getStatusCode()) {
            case 1 -> HttpStatus.NOT_FOUND;
            case 2 -> HttpStatus.UNAUTHORIZED;
            case 3 -> HttpStatus.BAD_REQUEST;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        }, apiException.getMessage());
    }
}
