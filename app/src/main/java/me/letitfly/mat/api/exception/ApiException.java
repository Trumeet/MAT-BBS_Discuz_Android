package me.letitfly.mat.api.exception;

/**
 * Created by Trumeet on 2017/3/4.
 * Forum api exception, throw when HttpResult unsuccessful.
 * @see me.letitfly.mat.api.model.HttpResult
 * @author Trumeet
 */

public class ApiException extends RuntimeException {
    public ApiException() {
        super();
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }
}
