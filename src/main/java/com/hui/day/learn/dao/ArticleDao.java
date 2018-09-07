package com.hui.day.learn.dao;

import com.hui.day.learn.controller.params.GetArticleParams;
import com.hui.day.learn.response.dto.ArticleVO;
import com.hui.day.learn.response.dto.PageDto;

public interface ArticleDao {

    PageDto<ArticleVO> getArticlePage(GetArticleParams params);
}
