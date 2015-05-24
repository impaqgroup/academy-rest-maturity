package com.impaq.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;

import com.impaq.rest.ResponseInfo;


@ControllerAdvice
public class DefaultExceptionHandler {

    
        @ResponseStatus(HttpStatus.OK) 
        @ExceptionHandler(RuntimeException.class)
        public @ResponseBody ResponseInfo handleError(HttpServletRequest req, Exception ex) {
            return new ResponseInfo(true, ex.getMessage());
    }
}
