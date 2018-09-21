package com.hui.day.learn.dao;

import com.hui.day.learn.domain.TbWordBook;

import java.util.List;

/**
 * description ：单词相关的数据库操作
 *
 * @author ：huim_lin.
 * @date ：Created in 2018/9/21
 */
public interface WordDao {

    /**
     * 获取单词本
     * @param userId 用户id
     * @param bookId 单词本id
     * @return TbWordBook
     */
    TbWordBook getWordBook(long userId, long bookId);

    /**
     * 获取单词本
     * @param userId 用户id
     * @return TbWordBook列表
     */
    List<TbWordBook> getWordBook(long userId);
}
