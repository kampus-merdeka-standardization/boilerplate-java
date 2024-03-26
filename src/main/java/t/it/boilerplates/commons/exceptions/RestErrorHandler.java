package t.it.boilerplates.commons.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import t.it.boilerplates.interfaces.models.responses.WebResponse;

@Slf4j
@RestControllerAdvice
public class RestErrorHandler {
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<WebResponse<String>> constraintViolationException(ConstraintViolationException constraintViolationException) {
        log.warn("Error", constraintViolationException);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(WebResponse.<String>builder().errors(String.join(", ", constraintViolationException.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList())).build());
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseBody
    public ResponseEntity<WebResponse<String>> responseStatusException(ResponseStatusException responseStatusException) {
        log.warn("Error", responseStatusException);
        return ResponseEntity.status(responseStatusException.getStatusCode())
                .body(WebResponse.<String>builder().errors(responseStatusException.getReason()).build());
    }
}
