package com.hui.day.learn.controller;

import com.hui.day.learn.controller.params.ArticleParams;
import com.hui.day.learn.controller.params.GetArticleParams;
import com.hui.day.learn.controller.params.ParagraphParams;
import com.hui.day.learn.response.RestResponse;
import com.hui.day.learn.response.codes.Rest2Code;
import com.hui.day.learn.response.dto.ArticleDetailVO;
import com.hui.day.learn.response.dto.ArticleVO;
import com.hui.day.learn.response.dto.PageDto;
import com.hui.day.learn.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author huim_lin
 * */
@Api("添加文章接口")
@RestController
@RequestMapping(path = "/article",headers = BaseController.TOKEN)
public class ArticleController extends BaseController{
    @Resource
    private ArticleService articleService;

    @ApiOperation("添加文章基本信息")
    @PostMapping("/add")
    public RestResponse<Boolean> addArticle(
            @RequestBody ArticleParams params){
        try {
            return RestResponse.ok(articleService.addArticle(params));
        } catch (Exception e) {
            return RestResponse.exception(Rest2Code.REST_ILLEGAL_PARAMS);
        }
    }

    @ApiOperation("给文章添加段落")
    @PostMapping("/addParagraph")
    public RestResponse<Boolean> addParagraph(
            @RequestBody ParagraphParams params){
        return RestResponse.ok(articleService.addParagraph(params));
    }

    @ApiOperation("获取文章列表")
    @PostMapping("/getArticle")
    public RestResponse<PageDto<ArticleVO>> getArticlePage(
            @RequestBody GetArticleParams params){
        if (params.getPageIndex()==null || params.getPageIndex()<0){
            params.setPageIndex(0);
        }
        if (params.getPageSize()==null || params.getPageSize()<0){
            params.setPageSize(20);
        }
        return RestResponse.ok(articleService.getArticlePage(params));
    }

    @ApiModelProperty("获取文章详情")
    @GetMapping("/getArticle/{articleId}")
    public RestResponse<ArticleDetailVO> getArticleDetail(
            @PathVariable("articleId") Long articleId){
        return RestResponse.ok(articleService.getArticleDetail(articleId));
    }
}
