package com.github.xiaomao23zhi.core.handler;

import com.github.xiaomao23zhi.core.constant.ErrorCode;

/**
 * @author xiaomao23zhi
 */
public class JSONResponseBuilder {

    private JSONResponseBuilder() {
    }

    public static <T> JSONResponse<T> success(T data) {
        return makeResponse(ErrorCode.SUCCESS, ErrorCode.SUCCESS.getMessage(), data);
    }

    public static <T> JSONResponse<T> fail(ErrorCode errorCode, String message) {
        return makeResponse(errorCode, message, null);
    }

    private static <T> JSONResponse<T> makeResponse(ErrorCode errorCode, String message, T data) {
        return new JSONResponse<>(errorCode.getCode(),
                errorCode == ErrorCode.SUCCESS ? errorCode.getMessage() : message, data);
    }
}
