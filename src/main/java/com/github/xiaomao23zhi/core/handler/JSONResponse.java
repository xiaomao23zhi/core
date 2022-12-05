package com.github.xiaomao23zhi.core.handler;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @param <T>
 * @author xiaomao23zhi
 */
@ApiModel(value = "JSON formatted response")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JSONResponse<T> implements Serializable {

    private static final long serialVersionUID = 3982325364686829124L;

    /* 返回码 */
    private Integer code;

    /* 返回消息 */
    private String message;

    /* 返回封装数据 */
    private T body;

    public JSONResponse(T body) {
        this.body = body;
    }
}
