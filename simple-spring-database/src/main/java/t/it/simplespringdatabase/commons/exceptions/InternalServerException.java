package t.it.simplespringdatabase.commons.exceptions;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class InternalServerException extends ApiException {

    public InternalServerException(final String message) {
        super(500, message);
    }
}
