package com.hui.day.learn.service;

import com.hui.day.learn.controller.params.ArticleParams;
import com.hui.day.learn.controller.params.GetArticleParams;
import com.hui.day.learn.controller.params.ParagraphParams;
import com.hui.day.learn.controller.params.SentencePointParams;
import com.hui.day.learn.response.dto.ArticleDetailVO;
import com.hui.day.learn.response.dto.ArticleVO;
import com.hui.day.learn.response.dto.PageDto;

/**
 * @author huim_lin
 * */
public interface ArticleService {

    /**
     * 添加文章
     * @param params 参数
     * @return true-添加成功
     * @throws Exception
     */
    boolean addArticle(ArticleParams params) throws Exception;

    /**
     * 添加段落
     * @param params 参数
     * @return true-成功
     */
    boolean addParagraph(ParagraphParams params);

    /**
     * 分页获取文章列表
     * @param params 参数
     * @return ArticleVO列表
     */
    PageDto<ArticleVO> getArticlePage(GetArticleParams params);

    /**
     * 获取文章详情
     * @param articleId 文章id
     * @return ArticleDetailVO
     */
    ArticleDetailVO getArticleDetail(Long articleId);

    /**
     * 设置句子音视频时间节点
     * @param params 参数
     * @return true-成功
     */
    boolean setSentencePoint(SentencePointParams params);
}
