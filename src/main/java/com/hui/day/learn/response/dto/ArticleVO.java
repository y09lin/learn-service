package com.hui.day.learn.response.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author huim_lin
 * */

@AllArgsConstructor
@Data
public class ArticleVO {
    public ArticleVO(){}

    @ApiModelProperty("文章id")
    private Long articleId;

    @ApiModelProperty("类型名称")
    private String type;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("日期")
    private String date;

    @ApiModelProperty("文章来源")
    private String source;
}
