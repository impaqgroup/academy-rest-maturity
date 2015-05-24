package com.impaq.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.impaq.rest.ResponseInfo;


@ControllerAdvice
public class DefaultExceptionHandler {

    
        @ResponseStatus(HttpStatus.OK)  // 409
        @ExceptionHandler(UserNotFoundException.class)
        public ResponseInfo handleConflict() {
            return new ResponseInfo(true, "");
    }
}
