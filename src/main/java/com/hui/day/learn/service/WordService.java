package com.hui.day.learn.service;

import com.hui.day.learn.domain.TbWordBook;
import com.hui.day.learn.response.dto.WordBookVO;
import com.hui.day.learn.response.dto.WordVO;

import java.util.List;

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

    /**
     * 创建单词本
     * @param userId 用户id
     * @param bookName 单词本名称
     * @return WordBookVO
     */
    WordBookVO createBook(long userId,String bookName);

    /**
     * 获取单词本
     * @param userId 用户id
     * @param bookId 单词本id
     * @return WordBookVO
     */
    WordBookVO getWordBook(long userId, long bookId);

    /**
     * 获取单词本
     * @param userId 用户id
     * @return WordBookVO列表
     */
    List<WordBookVO> getWordBook(long userId);
}
