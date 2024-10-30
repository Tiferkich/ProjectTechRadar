package ru.sakhdanil.TechRadar.exception;

import org.springframework.http.HttpStatus;


public class TechnologyNotFoundExeption extends BaseException {

    public TechnologyNotFoundExeption() {
        super(new ApiError("Not found ", 404), HttpStatus.NOT_FOUND);
    }

    public TechnologyNotFoundExeption(Long id) {
        super(new ApiError("Not found technology with id : " + id, 404), HttpStatus.NOT_FOUND);
    }
}
