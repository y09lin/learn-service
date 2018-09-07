package com.hui.day.learn.repository;

import com.hui.day.learn.domain.TbArticle;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleRepository extends PagingAndSortingRepository<TbArticle,Long>,
        JpaSpecificationExecutor<TbArticle>{

    TbArticle findByArticleId(long articleId);
}
