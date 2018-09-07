package com.hui.day.learn.controller.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ArticleParams {
    @ApiModelProperty(value = "文章类型\n" +
            "* 1 - VOA",required = true,example = "1")
    private Integer type;

    @ApiModelProperty(value = "文章标题",required = true)
    private String title;

    @ApiModelProperty("MP3下载地址")
    private String mp3;

    @ApiModelProperty(value = "文章来源",required = true)
    private String source;

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("时间")
    private String date;

    @ApiModelProperty("段落")
    private List<ParagraphParams> pList;
}
