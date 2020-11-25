package com.artemisias.web.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Result<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    public static Result<Object> success(Object data)
    {
        Result<Object> success = Result.builder().code(200)
                .message("success")
                .data(data).build();
        return success;
    }
}
