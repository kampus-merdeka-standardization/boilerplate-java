package t.it.simplespringapp.commons.exceptions;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class NotFoundResourceException extends ApiException {

    public NotFoundResourceException(final String message) {
        super(1, message);
    }
}
