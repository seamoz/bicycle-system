package com.ps.allapp.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ZZH
 * @date 2019/8/14 15:42
 */
@Data
public class Result<T> implements Serializable {

    private String meg;

    private int error_code;

    private T data;

    public Result(String meg, int error_code) {
        this.meg = meg;
        this.error_code = error_code;
    }

    public Result() {
    }

    public Result(String meg, int error_code, T data) {
        this.meg = meg;
        this.error_code = error_code;
        this.data = data;
    }
}
