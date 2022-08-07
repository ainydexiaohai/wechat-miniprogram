package com.liuhangs.learning.wechatminiprogram.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private String code;

    private String msg;

    private T content;

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getCode();
    }

    public static Result<Void> success() {
        return new Result<>(ErrorCode.SUCCESS);
    }

    public static <T> Result<T> success(T content) {
        Result<T> result = new Result<>(ErrorCode.SUCCESS);
        result.setContent(content);
        return result;
    }

    public static Result<Void> error(ErrorCode errorCode) {
        return new Result<>(errorCode);
    }

    public static Result<Void> error(String code, String msg) {
        return new Result<>(code, msg);
    }
}
