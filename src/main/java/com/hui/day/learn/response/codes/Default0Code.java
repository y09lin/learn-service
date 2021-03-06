package com.hui.day.learn.response.codes;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author huim_lin
 * */
@AllArgsConstructor
@Getter
public enum Default0Code implements CodeEnum {
    /**
     *
     * */
    OK(0,"成功"),
    INTERNAL_SERVER_ERROR(100000001, "未处理异常"),
    DATE_ERROR(100000002, "日期错误"),
    ILLEGAL_PARAMS(100000003,"非法参数");

    private int code;
    private String defaultMessage;

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public long getCode() {
        return this.code;
    }

    @Override
    public String getDefaultMessage() {
        return this.defaultMessage;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("code", code)
                .add("defaultMessage", defaultMessage)
                .toString();
    }
}
