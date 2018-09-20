package com.hui.day.learn.repository;

import com.hui.day.learn.domain.TbVoa;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author huim_lin
 * */
public interface VoaRepository extends PagingAndSortingRepository<TbVoa,Long>,
        JpaSpecificationExecutor<TbVoa>{
}
