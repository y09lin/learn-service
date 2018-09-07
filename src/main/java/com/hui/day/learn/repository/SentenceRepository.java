package com.hui.day.learn.repository;

import com.hui.day.learn.domain.TbSentence;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SentenceRepository extends PagingAndSortingRepository<TbSentence,Long>,
        JpaSpecificationExecutor<TbSentence>{
}
