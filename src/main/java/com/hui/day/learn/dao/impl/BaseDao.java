package com.hui.day.learn.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Map;

class BaseDao {

    /**
     * 给SQL设置参数
     * @param query Query
     * @param params 参数map
     */
    void setQueryParams(Query query, Map<String, Object> params){
        if (params != null) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
    }

    /**
     * 获取总数
     * @param entityManager EntityManager
     * @param sql SQL语句
     * @param params 参数
     * @return 总数
     */
    long getCount(EntityManager entityManager,String sql,Map<String, Object> params){
        StringBuilder countSb=new StringBuilder("select count(1) from");
        countSb.append(" (").append(sql).append(" ) as temp");
        Query query=entityManager.createNativeQuery(countSb.toString());
        setQueryParams(query,params);
        try {
            return ((BigInteger)query.getResultList().get(0)).longValue();
        } catch (Exception e) {
            return 0L;
        }
    }
}
