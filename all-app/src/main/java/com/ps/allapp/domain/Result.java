package com.ps.allapp.domain;

import lombok.Data;

/**
 * @author ZZH
 * @date 2019/8/14 15:42
 */
@Data
public class Result<T> {

    private String meg;

    private int error_code;

    private T data;

}
