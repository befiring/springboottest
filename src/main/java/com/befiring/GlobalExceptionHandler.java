package com.befiring;

import com.befiring.entity.ReturnValue;
import com.befiring.exception.RequestException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RequestException.class)
    @ResponseBody
    public ReturnValue HandleRequestException(RequestException e){
        return new ReturnValue.Builder(false).code(e.getCode()).message(e.getMessage()).build();
    }
}
