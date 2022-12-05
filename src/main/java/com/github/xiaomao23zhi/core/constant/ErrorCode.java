package com.github.xiaomao23zhi.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiaomao23zhi
 */
@AllArgsConstructor
@Getter
public enum ErrorCode {

    SUCCESS(200, "请求成功"),
    CREATED(201, "已创建"),
    ACCEPTED(202, "已接受"),

    // Client errors
    BAD_REQUEST(400, "错误请求"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "找不到给定资源"),
    METHOD_NOT_ALLOWED(405, "禁止的方法"),

    // Server errors
    INTERNAL_SERVER_ERROR(500, "内部错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    REDIS_LOCK_ERROR(504, "分布式锁切面异常"),

    // General errors 60X
    IO_EXCEPTION(600, "无法打开文件"),
    INTERRUPTED_EXCEPTION(601, "线程中断异常"),
    BAD_DATETIME_FORMAT(602, "错误时间的格式"),
    DB_DUPLICATE_KEY(603, "主键重复"),
    ENUM_CONSTANT_NOT_PRESENT(604, "枚举值不存在"),
    JSON_PROCESS_ERROR(605, "JSON格式错误"),

    // Controller errors 61X - 62X


    // Service errors 63X - 64X


    // Feign errors 65X - 66X
    URI_SYNTAX_EXCEPTION(670, "URI地址格式错误"),
    ;

    private final int code;
    private final String message;

}
