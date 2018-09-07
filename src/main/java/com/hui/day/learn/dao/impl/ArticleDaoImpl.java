package com.hui.day.learn.dao.impl;

import com.hui.day.learn.controller.params.GetArticleParams;
import com.hui.day.learn.dao.ArticleDao;
import com.hui.day.learn.response.dto.ArticleVO;
import com.hui.day.learn.response.dto.PageDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wahaha
 * */
@Slf4j
@Repository("articleDao")
public class ArticleDaoImpl extends BaseDao implements ArticleDao {
    @Autowired
    private EntityManager entityManager;

    @Override
    public PageDto<ArticleVO> getArticlePage(GetArticleParams params) {
        Map<String,Object> sqlParams=new HashMap<>(2);
        StringBuilder sql=new StringBuilder("select a.article_id as articleId,a.title,a.author,a.date,a.source,")
                .append("(select d.name from tb_dict d " +
                        "where d.code = a.dict_code and d.type = '001' and d.status = 1) as type ")
                .append(" from tb_article a")
                .append(" where a.status = 1");
        if (!StringUtils.isBlank(params.getAuthor())){
            sql.append(" and a.author like :author ");
            sqlParams.put("author","'%"+params.getAuthor()+"%'");
        }
        if (!StringUtils.isBlank(params.getTitle())){
            sql.append(" and a.title like :title ");
            sqlParams.put("title","'%"+params.getTitle()+"%'");
        }
        Query query=entityManager.createNativeQuery(sql.toString(),"ArticleVOMapping");
        setQueryParams(query,sqlParams);
        PageDto<ArticleVO> page=new PageDto<>();
        long totalElement=getCount(entityManager,sql.toString(),sqlParams);
        long totalPage= totalElement % params.getPageSize() == 0 ? totalElement / params.getPageSize() :
                totalElement / params.getPageSize() +1;
        page.setTotalElement(totalElement);
        page.setTotalPage(totalPage);

        query.setFirstResult(params.getPageIndex() * params.getPageSize());
        query.setMaxResults(params.getPageSize());
        List<ArticleVO> list=query.getResultList();
        page.setDataList(list);
        page.setPageIndex(params.getPageIndex());
        page.setPageSize(params.getPageSize());
        return page;
    }
}
