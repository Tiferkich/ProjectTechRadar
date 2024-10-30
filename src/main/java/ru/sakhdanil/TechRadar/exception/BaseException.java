package ru.sakhdanil.TechRadar.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {
    private final HttpStatus httpStatus;

    public BaseException(ApiError apiError, HttpStatus httpStatus) {
        super(apiError.getMessage());
        this.httpStatus = httpStatus;
    }

}
