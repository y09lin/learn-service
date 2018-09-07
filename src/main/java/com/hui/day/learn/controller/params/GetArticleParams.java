package com.hui.day.learn.controller.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetArticleParams {
    @ApiModelProperty("根据标题搜索")
    private String title;

    @ApiModelProperty("根据作者搜索")
    private String author;

    @ApiModelProperty(value = "页码，默认从0开始。",example = "0")
    private Integer pageIndex;

    @ApiModelProperty(value = "条数，默认20条。",example = "20")
    private Integer pageSize;
}
