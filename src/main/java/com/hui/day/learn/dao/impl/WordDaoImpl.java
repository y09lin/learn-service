package com.hui.day.learn.dao.impl;

import com.hui.day.learn.dao.WordDao;
import com.hui.day.learn.domain.TbWord;
import com.hui.day.learn.domain.TbWordBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
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

    @Override
    public boolean hasBook(long userId, long bookId) {
        TbWordBook book = getWordBook(userId, bookId);
        return book!=null;
    }

    @Override
    public Page<TbWord> getWords(long userId, long bookId, List<Integer> orderList,
                                 List<Integer> sortList, int pageIndex, int pageSize) {
        StringBuilder sb = new StringBuilder("SELECT w.* FROM tb_word w ")
                .append("LEFT JOIN tb_user_word u ON w.word_id = u.word_id AND u.user_id = :userId ")
                .append("WHERE u.book_id = :bookId");
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("bookId", bookId);
        long count = getCount(em,sb.toString(),map);
        Pageable pageable = PageRequest.of(pageIndex,pageSize);
        if (pageIndex * pageSize + pageSize > count){
            return new PageImpl<>(new ArrayList<>(), pageable, count);
        }
        sb.append(wordOrder(orderList, sortList));
        Query query = em.createNativeQuery(sb.toString(), TbWord.class);
        setQueryParams(query, map);
        return new PageImpl<>(query.getResultList(), pageable, count);
    }

    private String wordOrder(List<Integer> orderList,List<Integer> sortList){
        if (orderList == null || orderList.isEmpty()){
            return "";
        }
        StringBuilder sb = new StringBuilder("ORDER BY ");
        for (int i=0; i<orderList.size(); i++){
            int order = orderList.get(i);
            int sort = sortList.get(i);
            switch (order) {
                case 1 :
                    sb.append("w.word ");
                    break;
                case 2 :
                    sb.append("u.add_time ");
                    break;
                case 3 :
                    sb.append("u.grasp_level ");
                    break;
                default:
                    sb.append("w.word ");
                    break;
            }
            if (1 == sort){
                sb.append("ASC ");
            }else {
                sb.append("DESC ");
            }
        }
        return sb.toString();
    }
}
