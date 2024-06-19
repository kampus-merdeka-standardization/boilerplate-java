package t.it.simplespringdatabase.domains.services;

import jakarta.validation.ValidationException;
import reactor.core.publisher.Mono;

public interface ValidationService {
    <T> Mono<ValidationException> validateObject(T object);
}