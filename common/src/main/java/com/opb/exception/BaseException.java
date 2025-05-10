package com.opb.exception;

/**
 * 业务异常
 */
public class BaseException extends RuntimeException{

    public BaseException() {}

    public BaseException(String message) {
        super(message);
    }
}
