package com.github.xiaomao23zhi.core.handler;

import com.github.xiaomao23zhi.core.constant.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author xiaomao23zhi
 */
@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public <T> JSONResponse<T> defaultErrorHandler(HttpServletRequest request, Exception e) {

        if (e instanceof NoHandlerFoundException) {

            return JSONResponseBuilder.fail(ErrorCode.NOT_FOUND, "NoHandlerFoundException");

        } else if (e instanceof HttpClientErrorException.BadRequest) {

            return JSONResponseBuilder.fail(ErrorCode.BAD_REQUEST, "BadRequest");

        } else if (e instanceof HttpClientErrorException.Unauthorized) {

            return JSONResponseBuilder.fail(ErrorCode.UNAUTHORIZED, "Unauthorized");

        } else if (e instanceof HttpClientErrorException.Forbidden) {

            return JSONResponseBuilder.fail(ErrorCode.FORBIDDEN, "Forbidden");

        } else if (e instanceof MethodArgumentNotValidException) {

            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            String message = Objects.requireNonNull(bindingResult.getFieldError()).getField() + bindingResult.getFieldError().getDefaultMessage();

            return JSONResponseBuilder.fail(ErrorCode.METHOD_NOT_ALLOWED, message);

        } else if (e instanceof DuplicateKeyException) {

            return JSONResponseBuilder.fail(ErrorCode.DB_DUPLICATE_KEY, ErrorCode.DB_DUPLICATE_KEY.getMessage());

        } else {
            return JSONResponseBuilder.fail(ErrorCode.INTERNAL_SERVER_ERROR, "ERROR:" + e + Arrays.toString(e.getStackTrace()));
        }
    }
}
