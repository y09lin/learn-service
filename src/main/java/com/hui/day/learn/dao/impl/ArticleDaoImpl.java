package com.hui.day.learn.dao.impl;

import com.hui.day.learn.controller.params.GetArticleParams;
import com.hui.day.learn.dao.ArticleDao;
import com.hui.day.learn.response.dto.*;
import com.hui.day.learn.utils.StringHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
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

    @Override
    public ArticleDetailVO getArticle(Long articleId) {
        StringBuilder sb = new StringBuilder("SELECT a.article_id,a.title,a.author,a.date,a.source,a.mp3, ")
                .append("(SELECT d.`name` FROM tb_dict d WHERE d.`code` = a.dict_code ")
                .append("AND d.type = '001' AND d.`status`= 1) AS type ")
                .append("FROM tb_article a WHERE a.article_id = :articleId");
        Query query = entityManager.createNativeQuery(sb.toString());
        query.setParameter("articleId",articleId);
        List<Object[]> list = query.getResultList();
        if (list!=null && list.size()>0){
            return getArticleFromDB(list.get(0));
        }
        return null;
    }

    private ArticleDetailVO getArticleFromDB(Object[] objects){
        ArticleDetailVO vo = new ArticleDetailVO();
        vo.setArticleId(StringHelper.object2Long(objects[0]));
        vo.setTitle(StringHelper.object2String(objects[1]));
        vo.setAuthor(StringHelper.object2String(objects[2]));
        vo.setDate(StringHelper.object2String(objects[3]));
        vo.setSource(StringHelper.object2String(objects[4]));
        vo.setMp3(StringHelper.object2String(objects[5]));
        vo.setType(StringHelper.object2String(objects[6]));
        return vo;
    }

    @Override
    public List<ParagraphVO> getParagraphList(Long articleId) {
        StringBuilder sb = new StringBuilder("SELECT paragraph_id,article_id FROM tb_paragraph ")
                .append("WHERE article_id = :articleId");
        Query query = entityManager.createNativeQuery(sb.toString());
        query.setParameter("articleId",articleId);
        List<Object[]> list = query.getResultList();
        List<ParagraphVO> paragraphList = getParagraphFromDB(list);
        List<SentenceVO> sentenceList = getSentence(articleId);
        setSentences(paragraphList,sentenceList);
        return paragraphList;
    }

    private List<ParagraphVO> getParagraphFromDB(List<Object[]> list){
        List<ParagraphVO> voList = new ArrayList<>();
        if (list!=null && list.size()>0){
            for (Object[] objects:list){
                ParagraphVO vo = new ParagraphVO();
                vo.setParagraphId(StringHelper.object2Long(objects[0]));
                vo.setArticleId(StringHelper.object2Long(objects[1]));
                voList.add(vo);
            }
        }
        return voList;
    }

    private void setSentences(List<ParagraphVO> paragraphList,List<SentenceVO> sentenceList){
        if (paragraphList == null || paragraphList.isEmpty()
                || sentenceList == null || sentenceList.isEmpty()){
            return;
        }
        for (ParagraphVO p:paragraphList){
            List<SentenceVO> slist = new ArrayList<>();
            for (SentenceVO s:sentenceList){
                if (p.getParagraphId().equals(s.getParagraphId())){
                    slist.add(s);
                }
            }
            p.setSentenceList(slist);
        }
    }

    private List<SentenceVO> getSentence(Long articleId){
        StringBuilder sb = new StringBuilder("SELECT s.* FROM tb_sentence s ")
                .append("LEFT JOIN tb_paragraph p ON s.paragraph_id = p.paragraph_id ")
                .append("WHERE p.article_id = :articleId ORDER BY sentence_id");
        Query query = entityManager.createNativeQuery(sb.toString());
        query.setParameter("articleId",articleId);
        List<Object[]> list = query.getResultList();
        return getSentenceFromDB(list);
    }

    private List<SentenceVO> getSentenceFromDB(List<Object[]> list){
        List<SentenceVO> voList = new ArrayList<>();
        if (list!=null && list.size()>0){
            for (Object[] objects:list){
                SentenceVO vo = new SentenceVO();
                voList.add(vo);
                vo.setSentenceId(StringHelper.object2Long(objects[0]));
                vo.setParagraphId(StringHelper.object2Long(objects[1]));
                vo.setContent(StringHelper.object2String(objects[2]));
                vo.setBeginPoint(StringHelper.object2Integer(objects[3]));
                vo.setEndPoint(StringHelper.object2Integer(objects[4]));
            }
        }
        return voList;
    }
}
