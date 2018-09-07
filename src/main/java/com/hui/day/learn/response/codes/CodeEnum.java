package com.hui.day.learn.response.codes;

public interface CodeEnum {
    /**
     * 获取枚举的 Name
     */
    String getName();

    /**
     * 获取 code
     */
    long getCode();

    /**
     * 获取默认消息
     */
    String getDefaultMessage();
}
