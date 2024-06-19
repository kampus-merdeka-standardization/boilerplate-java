package t.it.simplespringdatabase.commons.exceptions;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class NotFoundResourceException extends ApiException {

    public NotFoundResourceException(final String message) {
        super(404, message);
    }
}
