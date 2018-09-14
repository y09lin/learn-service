package com.hui.day.learn.service.impl;

import com.hui.day.learn.controller.params.*;
import com.hui.day.learn.dao.ArticleDao;
import com.hui.day.learn.domain.TbArticle;
import com.hui.day.learn.domain.TbParagraph;
import com.hui.day.learn.domain.TbSentence;
import com.hui.day.learn.repository.ArticleRepository;
import com.hui.day.learn.repository.ParagraphRepository;
import com.hui.day.learn.repository.SentenceRepository;
import com.hui.day.learn.repository.VoaRepository;
import com.hui.day.learn.response.dto.ArticleDetailVO;
import com.hui.day.learn.response.dto.ArticleVO;
import com.hui.day.learn.response.dto.PageDto;
import com.hui.day.learn.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author wahaha
 * */
@Slf4j
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private VoaRepository voaRepository;

    @Autowired
    private ParagraphRepository paragraphRepository;

    @Autowired
    private SentenceRepository sentenceRepository;

    @Autowired
    private ArticleDao articleDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addArticle(ArticleParams params) throws Exception{
        TbArticle article=new TbArticle();
        article.setTitle(params.getTitle());
        article.setMp3(params.getMp3());
        article.setSource(params.getSource());
        article.setUploadTime(new Date());
        article.setUploadUser(1L);
        article.setAuthor(params.getAuthor());
        article.setDate(params.getDate());
        article.setDictCode(params.getType());
        List<ParagraphParams> pList=params.getPList();
        article.setParagraphCount(pList==null? 0:pList.size());
        articleRepository.save(article);

        if (pList!=null && pList.size()>0){
            article.setParagraphCount(pList.size());
            article=articleRepository.save(article);
            List<TbSentence> sentenceList=new ArrayList<>();
            for (ParagraphParams p:pList){
                List<String> sList = p.getSentenceList();
                if (sList==null || sList.size()==0){
                    continue;
                }
                TbParagraph paragraph=new TbParagraph();
                paragraph.setArticleId(article.getArticleId());
                paragraph.setSentenceCount(sList.size());
                paragraph=paragraphRepository.save(paragraph);
                for (String s:sList){
                    TbSentence tbs=new TbSentence();
                    tbs.setParagraphId(paragraph.getParagraphId());
                    tbs.setContent(s);
                    sentenceList.add(tbs);
                }
            }
            sentenceRepository.saveAll(sentenceList);
            return true;
        }
        throw new Exception("save article error");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addParagraph(ParagraphParams params) {
        if (params.getSentenceList()==null || params.getSentenceList().size()==0){
            return false;
        }
        TbParagraph paragraph=new TbParagraph();
        paragraph.setArticleId(params.getArticleId());
        paragraph.setSentenceCount(params.getSentenceList().size());
        paragraph=paragraphRepository.save(paragraph);
        TbArticle article=articleRepository.findByArticleId(paragraph.getArticleId());
        article.setParagraphCount(article.getParagraphCount()+1);
        articleRepository.save(article);
        List<TbSentence> sentenceList=new ArrayList<>();
        for (String s:params.getSentenceList()){
            TbSentence tbs=new TbSentence();
            tbs.setParagraphId(paragraph.getParagraphId());
            tbs.setContent(s);
            sentenceList.add(tbs);
        }
        sentenceRepository.saveAll(sentenceList);
        return true;
    }

    @Override
    public PageDto<ArticleVO> getArticlePage(GetArticleParams params) {
        return articleDao.getArticlePage(params);
    }

    @Override
    public ArticleDetailVO getArticleDetail(Long articleId) {
        ArticleDetailVO vo = articleDao.getArticle(articleId);
        if (vo!=null){
            vo.setParagraphList(articleDao.getParagraphList(articleId));
        }
        return vo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean setSentencePoint(SentencePointParams params) {
        List<PointParams> pointList = params.getPointList();
        if (pointList == null || pointList.isEmpty()){
            return true;
        }
        List<Long> idList = new ArrayList<>();
        Map<Long,PointParams> map = new HashMap<>(pointList.size());
        for (PointParams p:pointList){
            idList.add(p.getSentenceId());
            map.put(p.getSentenceId(),p);
        }
        List<TbSentence> slist = articleDao.getSentences(idList);
        if (slist == null || slist.isEmpty()){
            return false;
        }
        for (TbSentence s:slist){
            PointParams p = map.get(s.getSentenceId());
            s.setBeginPoint(p.getBeginPoint());
            s.setEndPoint(p.getEndPoint());
        }
        sentenceRepository.saveAll(slist);
        return true;
    }
}
