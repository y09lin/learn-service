package com.hui.day.learn.repository;

import com.hui.day.learn.domain.TbDict;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DictRepository extends PagingAndSortingRepository<TbDict,Long>,
        JpaSpecificationExecutor<TbDict>{
    TbDict findByTypeAndAndCodeAndStatus(String type,int code,int status);
}
