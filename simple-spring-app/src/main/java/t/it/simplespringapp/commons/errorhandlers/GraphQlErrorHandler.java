package t.it.simplespringapp.commons.errorhandlers;


import graphql.ErrorClassification;
import graphql.GraphQLError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import t.it.simplespringapp.commons.exceptions.ApiException;

import java.util.Optional;

@Slf4j
@ControllerAdvice
public class GraphQlErrorHandler {
    @GraphQlExceptionHandler(ConstraintViolationException.class)
    public GraphQLError constraintViolationException(ConstraintViolationException constraintViolationException) {
        return GraphQLError.newError().errorType(ErrorType.BAD_REQUEST).message(String.join(", ", constraintViolationException.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList())).build();
    }

    @GraphQlExceptionHandler(ApiException.class)
    public GraphQLError apiException(ApiException apiException) {
        log.warn("Error", apiException);
        return GraphQLError.newError().errorType(Optional.ofNullable(mappedToGraphQlError(apiException)).orElse(ErrorType.INTERNAL_ERROR)).message(apiException.getMessage()).build();
    }

    private static ErrorClassification mappedToGraphQlError(ApiException apiException) {
        return switch (apiException.getStatusCode()) {
            case 1 -> ErrorType.NOT_FOUND;
            case 2 -> ErrorType.UNAUTHORIZED;
            case 3 -> ErrorType.BAD_REQUEST;
            default -> ErrorType.INTERNAL_ERROR;
        };
    }
}
