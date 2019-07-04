package org.elastos.record.utility.exception;

import lombok.Getter;
import org.elastos.record.utility.denum.Action;
import org.elastos.record.utility.exception.InsertNewInstanceException;

@Getter
public class ApiException extends InsertNewInstanceException {

    public ApiException(Action action) {
        super(action);
    }

    public ApiException(Action action, String message) {
        super(action, message);
    }
}
