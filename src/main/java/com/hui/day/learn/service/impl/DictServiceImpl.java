package com.hui.day.learn.service.impl;

import com.hui.day.learn.domain.TbDict;
import com.hui.day.learn.repository.DictRepository;
import com.hui.day.learn.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dictService")
public class DictServiceImpl implements DictService{
    @Autowired
    private DictRepository dictRepository;

    @Override
    public TbDict getDict(String type, int code) {
        return dictRepository.findByTypeAndAndCodeAndStatus(type,code,1);
    }
}
