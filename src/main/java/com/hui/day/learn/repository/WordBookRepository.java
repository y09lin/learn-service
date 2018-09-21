package com.hui.day.learn.repository;

import com.hui.day.learn.domain.TbWordBook;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * description ：单词本
 *
 * @author ：huim_lin.
 * @date ：Created in 2018/9/21
 */
public interface WordBookRepository extends PagingAndSortingRepository<TbWordBook,Long>,
        JpaSpecificationExecutor<TbWordBook> {
}
