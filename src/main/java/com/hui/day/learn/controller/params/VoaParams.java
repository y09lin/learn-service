package com.hui.day.learn.controller.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VoaParams {
    @ApiModelProperty("文章信息")
    private ArticleParams article;

    @ApiModelProperty("VOA信息")
    private VoaBean voa;
}
