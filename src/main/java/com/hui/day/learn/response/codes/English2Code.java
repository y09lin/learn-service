package com.hui.day.learn.response.codes;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author huim_lin
 * */
@AllArgsConstructor
@Getter
public enum English2Code implements CodeEnum {
    /**
     *
     * */
    TRANSLATE_ERROR(100020001, "翻译失败"),
    WORD_BOOK_NOT_EXIST(100020002, "单词本不存在");

    private final int code;
    private final String defaultMessage;

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
