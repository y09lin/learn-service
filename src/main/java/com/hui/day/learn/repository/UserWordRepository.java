package com.hui.day.learn.repository;

import com.hui.day.learn.domain.TbUserWord;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * description ：用户单词
 *
 * @author ：huim_lin.
 * @date ：Created in 2018/9/21
 */
public interface UserWordRepository extends PagingAndSortingRepository<TbUserWord,Long>,
        JpaSpecificationExecutor<TbUserWord> {
}
