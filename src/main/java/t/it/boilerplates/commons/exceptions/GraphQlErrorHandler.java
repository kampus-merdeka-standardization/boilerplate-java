package t.it.boilerplates.commons.exceptions;


import graphql.ErrorClassification;
import graphql.GraphQLError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

@Slf4j
@ControllerAdvice
public class GraphQlErrorHandler {
    @GraphQlExceptionHandler({ConstraintViolationException.class})
    public GraphQLError constraintViolationException(ConstraintViolationException constraintViolationException) {
        return GraphQLError.newError().errorType(ErrorType.BAD_REQUEST).message(String.join(", ", constraintViolationException.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList())).build();
    }

    @GraphQlExceptionHandler(ResponseStatusException.class)
    public GraphQLError responseStatusException(ResponseStatusException responseStatusException) {
        return GraphQLError.newError().errorType(Optional.ofNullable(TRANSLATED_GRAPHQL_ERROR.get(responseStatusException.getStatusCode().value())).orElse(ErrorType.INTERNAL_ERROR)).message(responseStatusException.getReason()).build();
    }

    private static final Map<Integer, ErrorClassification> TRANSLATED_GRAPHQL_ERROR = Map.of(
            HttpStatus.NOT_FOUND.value(), ErrorType.NOT_FOUND,
            HttpStatus.BAD_REQUEST.value(), ErrorType.BAD_REQUEST,
            HttpStatus.UNAUTHORIZED.value(), ErrorType.UNAUTHORIZED,
            HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorType.INTERNAL_ERROR
    );
}
