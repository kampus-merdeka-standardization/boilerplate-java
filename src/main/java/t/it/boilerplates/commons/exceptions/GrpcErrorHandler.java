package t.it.boilerplates.commons.exceptions;

import io.grpc.Status;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

@Slf4j
@GrpcAdvice
public class GrpcErrorHandler {
    @GrpcExceptionHandler({ConstraintViolationException.class})
    public Status constraintViolationException(ConstraintViolationException constraintViolationException) {
        log.warn("Error", constraintViolationException);
        return Status.INVALID_ARGUMENT.withDescription(String.join(", ", constraintViolationException.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList())).withCause(constraintViolationException);
    }

    @GrpcExceptionHandler(ResponseStatusException.class)
    public Status responseStatusException(ResponseStatusException responseStatusException) {
        log.warn("Error", responseStatusException);
        return Optional.ofNullable(TRANSLATED_GRPC_ERROR.get(responseStatusException.getStatusCode().value()))
                .orElse(Status.INTERNAL)
                .withDescription(responseStatusException.getReason()).withCause(responseStatusException);
    }

    private static final Map<Integer, Status> TRANSLATED_GRPC_ERROR = Map.of(
            HttpStatus.NOT_FOUND.value(), Status.NOT_FOUND,
            HttpStatus.BAD_REQUEST.value(), Status.INVALID_ARGUMENT,
            HttpStatus.UNAUTHORIZED.value(), Status.UNAUTHENTICATED,
            HttpStatus.INTERNAL_SERVER_ERROR.value(), Status.INTERNAL
    );
}
