package ru.sakhdanil.TechRadar.exception;


import org.springframework.http.HttpStatus;

public class RingNotFoundExeption extends BaseException {
    public RingNotFoundExeption() {
        super(new ApiError("This ring is not exist", 400), HttpStatus.BAD_REQUEST);
    }
}
