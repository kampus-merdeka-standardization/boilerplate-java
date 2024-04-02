package t.it.simplespringapp.commons.exceptions;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public sealed abstract class ApiException extends RuntimeException permits NotFoundResourceException, UnauthorizedException, IllegalParameterException, InternalServerException {
    private final int statusCode;
    private final String message;

    protected ApiException(final int statusCode, final String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
