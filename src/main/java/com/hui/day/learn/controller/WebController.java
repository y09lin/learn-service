package com.hui.day.learn.controller;

import com.hui.day.learn.response.dto.ArticleDetailVO;
import com.hui.day.learn.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * description ：
 *
 * @author ：huim_lin.
 * @date ：Created in 2018/9/17
 */
@Controller
public class WebController {
    @Resource
    private ArticleService articleService;

    @GetMapping("/article/{articleId}")
    public ModelAndView getArticle(@PathVariable("articleId") Long articleId){
        ModelAndView mv=new ModelAndView();
        ArticleDetailVO vo = articleService.getArticleDetail(articleId);
        mv.setViewName("SentencePointView");
        mv.addObject("articleDetail",vo);
        return mv;
    }
}
