package com.hui.day.learn.dao;

import com.hui.day.learn.controller.params.GetArticleParams;
import com.hui.day.learn.response.dto.ArticleDetailVO;
import com.hui.day.learn.response.dto.ArticleVO;
import com.hui.day.learn.response.dto.PageDto;
import com.hui.day.learn.response.dto.ParagraphVO;

import java.util.List;

/**
 * @author huim_lin
 * */
public interface ArticleDao {

    /**
     * 分页获取文章列表
     * @param params 参数
     * @return ArticleVO列表
     */
    PageDto<ArticleVO> getArticlePage(GetArticleParams params);

    /**
     * 获取文章详情，不带段落
     * @param articleId 文章id
     * @return ArticleDetailVO
     */
    ArticleDetailVO getArticle(Long articleId);

    /**
     * 根据文章id，获取段落列表
     * @param articleId 文章id
     * @return ParagraphVO列表
     */
    List<ParagraphVO> getParagraphList(Long articleId);
}
