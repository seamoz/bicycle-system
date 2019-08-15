package com.ps.allapp.exception;

import lombok.Data;

/**
 * @author ZZH
 * @date 2019/7/20 15:28
 */
@Data
public class BusinessException extends RuntimeException{

    private int error_code;

    private String msg;

    public BusinessException(int error_code, String msg) {
        this.error_code = error_code;
        this.msg = msg;
    }
}
