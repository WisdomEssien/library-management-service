package com.wis.libraryservice.exception;

import static com.wis.libraryservice.util.ResponseConstant.FAIL_INTERNAL_SERVER_ERROR_CODE;
import static com.wis.libraryservice.util.ResponseConstant.FAIL_INTERNAL_SERVER_ERROR_MESSAGE;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.wis.libraryservice.dto.response.BaseResponse;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public BaseResponse handleAllException(Exception ex, WebRequest request) {
    	log.error(FAIL_INTERNAL_SERVER_ERROR_MESSAGE + "\n", ex);
    	return new BaseResponse(FAIL_INTERNAL_SERVER_ERROR_CODE, FAIL_INTERNAL_SERVER_ERROR_MESSAGE);
    }
}
