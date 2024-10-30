package ru.sakhdanil.TechRadar.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({BaseException.class})
    public ResponseEntity<Object> handleBaseException(BaseException ex) {
        ApiError errorResponse = new ApiError(ex.getMessage(), ex.getHttpStatus().value());
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }


//    @ExceptionHandler({Exception.class})
//    public ResponseEntity<Object> handleInternalServerError() {
//        ApiError errorResponse = new ApiError("INTERNAL_SERVER_ERROR", 500);
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}

