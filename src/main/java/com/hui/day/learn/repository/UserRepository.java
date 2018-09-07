package com.hui.day.learn.repository;

import com.hui.day.learn.domain.TbUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<TbUser,Long>,
        JpaSpecificationExecutor<TbUser>{
}
