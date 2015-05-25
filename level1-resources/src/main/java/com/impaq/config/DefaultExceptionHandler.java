package com.impaq.config;

import com.impaq.rest.ResponseInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class DefaultExceptionHandler {

    
        @ResponseStatus(HttpStatus.OK) 
        @ExceptionHandler(RuntimeException.class)
        public @ResponseBody ResponseInfo handleError(HttpServletRequest req, Exception ex) {
            return new ResponseInfo(true, ex.getMessage());
    }
}
