package com.hui.day.learn.repository;

import com.hui.day.learn.domain.TbParagraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ParagraphRepository extends PagingAndSortingRepository<TbParagraph,Long>,
        JpaSpecificationExecutor<TbParagraph>{
}
