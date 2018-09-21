package com.hui.day.learn.dao;

import com.hui.day.learn.domain.TbWord;
import com.hui.day.learn.domain.TbWordBook;
import com.hui.day.learn.response.dto.PageDto;
import org.springframework.data.domain.Page;

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

    /**
     * 判断用户是否有该单词本
     * @param userId 用户id
     * @param bookId 单词本id
     * @return 有true
     */
    boolean hasBook(long userId, long bookId);

    /**
     * 分页获取单词
     * @param userId 用户id
     * @param bookId 单词本id
     * @param orderList 排序字段
     * @param sortList 升降
     * @param pageIndex 页码
     * @param pageSize 单词数
     * @return Page<TbWord>
     */
    Page<TbWord> getWords(long userId,long bookId, List<Integer> orderList,
                          List<Integer> sortList, int pageIndex, int pageSize);
}
