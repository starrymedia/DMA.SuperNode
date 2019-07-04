package org.elastos.record.utility.exception;

import lombok.Getter;
import org.elastos.record.utility.denum.Action;


@Getter
public class InsertNewInstanceException extends Exception {

    private String code ;
    public InsertNewInstanceException(Action action  ) {
        this(action,action.getMsg());
    }

    public InsertNewInstanceException(Action action ,String message) {
        super(message);
        this.code = action.getCode() ;
    }
}
