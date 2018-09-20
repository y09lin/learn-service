package com.hui.day.learn.service;

import com.hui.day.learn.response.dto.WordVO;

/**
 * description ：
 *
 * @author ：huim_lin.
 * @date ：Created in 2018/9/20
 */
public interface WordService {
    /**
     * 单词翻译
     * @param word 单词
     * @return WordVO
     */
    WordVO translate(String word);
}
