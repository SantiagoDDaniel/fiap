package com.fiap.ecommerce.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationResourceAdvice {
    public ApplicationResourceAdvice() {
    }

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ApiError handleBusinessException(BusinessException exception) {
        return new ApiError(exception.getMessage());
    }
}

