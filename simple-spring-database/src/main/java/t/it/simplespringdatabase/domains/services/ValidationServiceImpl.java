package t.it.simplespringdatabase.domains.services;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {
    private final Validator validator;

    @Override
    public <T> Mono<ValidationException> validateObject(T object) {
        final var constraintViolations = validator.validate(object);
        if (!constraintViolations.isEmpty()) return Mono.just(new ConstraintViolationException(constraintViolations));
        return Mono.empty();
    }
}