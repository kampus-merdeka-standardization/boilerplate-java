package t.it.boilerplates.commons.errorhandlers;

import io.grpc.Status;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import t.it.boilerplates.commons.exceptions.ApiException;

import java.util.Optional;

@Slf4j
@GrpcAdvice
public class GrpcErrorHandler {
    @GrpcExceptionHandler(ConstraintViolationException.class)
    public Status constraintViolationException(ConstraintViolationException constraintViolationException) {
        log.warn("Error", constraintViolationException);
        return Status.INVALID_ARGUMENT.withDescription(String.join(", ", constraintViolationException.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList())).withCause(constraintViolationException);
    }

    @GrpcExceptionHandler(ApiException.class)
    public Status apiException(ApiException apiException) {
        log.warn("Error", apiException);
        return Optional.ofNullable(mappedToGrpcStatus(apiException))
                .orElse(Status.INTERNAL)
                .withDescription(apiException.getMessage()).withCause(apiException);
    }


    private static Status mappedToGrpcStatus(ApiException apiException) {
        return switch (apiException.getStatusCode()) {
            case 1 -> Status.NOT_FOUND;
            case 2 -> Status.UNAUTHENTICATED;
            case 3 -> Status.INVALID_ARGUMENT;
            default -> Status.INTERNAL;
        };
    }
}
