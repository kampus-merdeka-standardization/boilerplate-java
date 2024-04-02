package t.it.boilerplates.commons.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class UnauthorizedException extends ApiException {

    public UnauthorizedException(final String message) {
        super(2, message);
    }
}
