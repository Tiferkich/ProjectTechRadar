package ru.sakhdanil.TechRadar.exception;

import lombok.Data;

@Data
public class ApiError {
    private final String message;
    private final int code;
}