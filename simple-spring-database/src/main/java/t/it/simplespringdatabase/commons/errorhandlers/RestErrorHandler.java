package t.it.simplespringdatabase.commons.errorhandlers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;
import t.it.simplespringdatabase.applications.payloads.responses.MetaResponse;
import t.it.simplespringdatabase.applications.payloads.responses.WebResponse;
import t.it.simplespringdatabase.commons.exceptions.ApiException;

@Slf4j
@RestControllerAdvice
public class RestErrorHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public Mono<ResponseEntity<WebResponse<String>>> constraintViolationException(ConstraintViolationException constraintViolationException) {
        log.warn("Error", constraintViolationException);
        String message = String.join(", ", constraintViolationException.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList());
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(WebResponse.<String>builder().meta(MetaResponse.builder().code(String.valueOf(HttpStatus.BAD_REQUEST.value())).message(message).build()).build()))
        ;
    }

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public Mono<ResponseEntity<WebResponse<String>>> apiException(ApiException apiException) {
        log.warn("Error", apiException);
        return Mono.just(ResponseEntity.status(apiException.getStatusCode())
                .body(WebResponse.<String>builder().meta(MetaResponse.builder().code(String.valueOf(apiException.getStatusCode())).message(apiException.getMessage()).build()).build()));
    }
}
