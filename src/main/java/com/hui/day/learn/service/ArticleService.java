package com.hui.day.learn.service;

import com.hui.day.learn.controller.params.ArticleParams;
import com.hui.day.learn.controller.params.GetArticleParams;
import com.hui.day.learn.controller.params.ParagraphParams;
import com.hui.day.learn.controller.params.VoaParams;
import com.hui.day.learn.response.dto.ArticleVO;
import com.hui.day.learn.response.dto.PageDto;

public interface ArticleService {

    boolean addArticle(ArticleParams params) throws Exception;

    boolean addParagraph(ParagraphParams params);

    PageDto<ArticleVO> getArticlePage(GetArticleParams params);
}
