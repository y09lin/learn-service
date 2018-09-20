package com.hui.day.learn.repository;

import com.hui.day.learn.domain.TbWord;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * description ：单词库相关JPA
 *
 * @author ：huim_lin.
 * @date ：Created in 2018/9/20
 */
public interface WordRepository extends PagingAndSortingRepository<TbWord,Long>,
        JpaSpecificationExecutor<TbWord> {
    /**
     * 查找单词
     * @param word 单词
     * @return TbWord
     */
    TbWord findByWord(String word);
}
