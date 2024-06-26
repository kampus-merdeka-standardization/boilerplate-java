package t.it.simplespringdatabase.commons.exceptions;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class IllegalParameterException extends ApiException {
    public IllegalParameterException(final String message) {
        super(400, message);
    }
}
