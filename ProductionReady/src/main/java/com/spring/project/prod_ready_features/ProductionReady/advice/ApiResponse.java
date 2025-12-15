package com.spring.project.prod_ready_features.ProductionReady.advice;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Data
@Getter
@Setter
public class ApiResponse<T> {

    private T data;
    private ApiError error;
    private LocalDateTime timeStamp;
    public ApiResponse()
    {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(ApiError error)
    {
        this.error = error;
    }

    public ApiResponse(T data)
    {
        this();
        this.data = data;
    }

}
