package com.hui.day.learn.dao.impl;

import com.hui.day.learn.dao.WordDao;
import com.hui.day.learn.domain.TbWordBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description ：
 *
 * @author ：huim_lin.
 * @date ：Created in 2018/9/21
 */
@Repository("wordDao")
public class WordDaoImpl extends BaseDao implements WordDao {
    @Autowired
    private EntityManager em;

    @Override
    public TbWordBook getWordBook(long userId, long bookId) {
        StringBuilder sb = new StringBuilder("SELECT * FROM tb_word_book ")
                .append("WHERE user_id = :userId AND book_id = :bookId;");
        Map<String,Object> map = new HashMap<>(2);
        map.put("userId",userId);
        map.put("bookId",bookId);
        Query query = em.createNativeQuery(sb.toString(),TbWordBook.class);
        setQueryParams(query,map);
        List<TbWordBook> list = query.getResultList();
        if (list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<TbWordBook> getWordBook(long userId) {
        StringBuilder sb = new StringBuilder("SELECT * FROM tb_word_book WHERE user_id = :userId;");
        Map<String,Object> map = new HashMap<>(2);
        map.put("userId",userId);
        Query query = em.createNativeQuery(sb.toString(),TbWordBook.class);
        setQueryParams(query,map);
        List<TbWordBook> list = query.getResultList();
        return list;
    }
}
