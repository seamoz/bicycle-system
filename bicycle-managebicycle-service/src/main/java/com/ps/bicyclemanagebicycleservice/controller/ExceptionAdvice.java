package com.ps.bicyclemanagebicycleservice.controller;

import com.ps.allapp.domain.Result;
import com.ps.allapp.exception.BusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZZH
 */
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result businessException(BusinessException e){
        return new Result(e.getMsg(),e.getError_code());
    }

}