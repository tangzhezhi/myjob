package org.tang.myjob.service.exception;

/**
 * Created by Administrator on 2015/3/23.
 */
public class BusinessRuntimeException extends RuntimeException {

    /** serialVersionUID */
    private static final long serialVersionUID = 2332608236621015980L;

    private int code;

    public BusinessRuntimeException() {
        super();
    }

    public BusinessRuntimeException(String message) {
        super(message);
    }

    public BusinessRuntimeException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessRuntimeException(Throwable cause) {
        super(cause);
    }

    public BusinessRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessRuntimeException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}