package com.SpringBoot.SpringLearningPhase.advices;

import com.SpringBoot.SpringLearningPhase.exception.ResourceNotfoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotfoundException.class)
    public ResponseEntity<ApiResponse> handleResourseNotFound(ResourceNotfoundException exception)
    {
        APIError apiError = APIError
                .builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleInterServerError(Exception exception)
    {
        APIError apiError = APIError
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleInputValidationErro(MethodArgumentNotValidException exception)
    {
        List<String> error = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .toList();

        APIError apiError = APIError
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Input Validatin Faild")
                .subError(error)
                .build();
        return buildErrorResponseEntity(apiError);
    }

    private ResponseEntity<ApiResponse> buildErrorResponseEntity(APIError apiError) {
        return  new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }


}
