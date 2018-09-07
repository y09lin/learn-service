package com.hui.day.learn.dao.impl;

import com.hui.day.learn.utils.BeanConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class CommonDao {
    private static EntityManager entityManager;

    private static NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public CommonDao(EntityManager entityManager,NamedParameterJdbcTemplate jdbcTemplate) {
        this.entityManager = entityManager;
        this.jdbcTemplate = jdbcTemplate;
    }

    public static <T> List<T> queryListEntity(String sql, Map<String, Object> params, Class<T> clazz){
        Session session = entityManager.unwrap(org.hibernate.Session.class);
        SQLQuery query = session.createSQLQuery(sql);
//        NativeQuery query= session.createNativeQuery(sql);

        if (params != null) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
//        nativeQuery.setResultSetMapping(Transformers.ALIAS_TO_ENTITY_MAP);
//        List result = nativeQuery.list();

        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List result =  query.list();
        if (result != null && result.size() > 0) {
            if (clazz != null) {
                List<T> beans = BeanConvertUtil.mapToBeansHump(result, clazz);
                return beans;
            }
            return result;
        }
        return null;
    }
}
