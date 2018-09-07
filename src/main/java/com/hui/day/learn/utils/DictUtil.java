package com.hui.day.learn.utils;

import com.hui.day.learn.config.ApplicationContextProvider;
import com.hui.day.learn.domain.TbDict;
import com.hui.day.learn.service.DictService;

/**
 * 数据字典工具类
 */
public class DictUtil {
    private static final String ARTICLE_TYPE= "001";

    private static DictService dictService;

    static {
        dictService = ApplicationContextProvider.getBean("dictService", DictService.class);
    }

    /**
     * 获取文章类型
     * */
    public static TbDict getAricleType(int code){
        return dictService.getDict(ARTICLE_TYPE,code);
    }
}
