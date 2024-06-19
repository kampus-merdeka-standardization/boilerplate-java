package t.it.simplespringdatabase.commons.exceptions;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class ConflictResourceException extends ApiException {

    public ConflictResourceException(final String message) {
        super(409, message);
    }
}
