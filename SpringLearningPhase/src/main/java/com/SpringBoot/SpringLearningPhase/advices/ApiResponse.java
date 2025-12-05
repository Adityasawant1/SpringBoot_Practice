package com.SpringBoot.SpringLearningPhase.advices;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class ApiResponse<T> {

    private T data;
    private APIError error;
    private LocalDateTime timeStamp;
    public ApiResponse()
    {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(APIError error)
    {
        this.error = error;
    }

    public ApiResponse(T data)
    {
        this();
        this.data = data;
    }

}
