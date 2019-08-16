package com.ps.allapp.domain;

import lombok.Data;

/**
 * @author ZZH
 * @date 2019/8/14 15:42
 */
@Data
public class Result<T> {
    public Result(String meg, int error_code) {
        this.meg = meg;
        this.error_code = error_code;
    }

    public Result() { }

    private String meg;

    private int error_code;

    private T data;

}
