package com.hui.day.learn.service;

import com.hui.day.learn.domain.TbDict;

public interface DictService {
    TbDict getDict(String type,int code);
}
